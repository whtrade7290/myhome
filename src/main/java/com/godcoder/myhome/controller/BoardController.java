package com.godcoder.myhome.controller;


import com.godcoder.myhome.Service.BoardService;
import com.godcoder.myhome.model.Board;
import com.godcoder.myhome.repository.BoardRepository;
import com.godcoder.myhome.validator.BoardValidator;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log
@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardValidator boardValidator;

    @Autowired
    private BoardService boardService;

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 2) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText) {
//        Page<Board> boards = boardRepository.findAll(pageable);
        Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = Math.max(1,boards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
        return "board/list";
    }


    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id){
        // @RequestParam을 사용하면 해당 파라미터가 반드시 있어야 하지만, 선택적으로 사용하려 하면 required = false를 하면 되고,
        // defalutValue로 default 값 설정도 가능함.
        if (id == null){
            // id가 null이면 쓰기 버튼과 같아짐
            // 동시에 새로운 Board VO(새로운 그릇)를 부여함
            model.addAttribute("board", new Board());
        } else {
            // findById > id로 찾았는데 없으면 null 리턴
            // 원래 findById 메서드를 통해 id로 원래 쓰던 Vo(그릇)을 찾음
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }

        return "board/form";
    }

    @PostMapping("/form")
    public String form(@Valid Board board, BindingResult bindingResult, Authentication authentication) {
        // bindingResult Board Class에서 지정한 size 값 검사
        boardValidator.validate(board, bindingResult);
        if (bindingResult.hasErrors()) {
            return "board/form";
        }
//        Authentication username = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
//        boardRepository.save(board);
        boardService.save(username, board);
        // redirect: /board/list 페이지 재조회
        return "redirect:/board/list";
    }
}
