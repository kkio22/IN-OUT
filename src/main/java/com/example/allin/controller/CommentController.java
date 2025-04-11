package com.example.allin.controller;

import com.example.allin.dto.*;
import com.example.allin.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService service;

    @PostMapping
    public ResponseEntity<CommentResponseDto> create(@RequestBody CommentRequestDto dto,
                                                     @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(201).body(service.create(dto, userDetails));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> read(@PathVariable Long id) {
        return ResponseEntity.ok(service.read(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDto> update(@PathVariable Long id, @RequestBody CommentRequestDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(Map.of("msg", "삭제 완료"));
    }

    @PutMapping("/{id}/like")
    public ResponseEntity<Map<String, String>> like(@PathVariable Long id,
                                                    @AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(Map.of("msg", service.like(id, user)));
    }

    @PutMapping("/{id}/unlike")
    public ResponseEntity<Map<String, String>> unlike(@PathVariable Long id,
                                                      @AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(Map.of("msg", service.unlike(id, user)));
    }
}
