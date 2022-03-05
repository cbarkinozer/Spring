package com.softtech.softtechspringboot.cmt.service;

import com.softtech.softtechspringboot.cmt.converter.CmtCommentMapper;
import com.softtech.softtechspringboot.cmt.dto.CmtCommentDto;
import com.softtech.softtechspringboot.cmt.entity.CmtComment;
import com.softtech.softtechspringboot.cmt.service.entityservice.CmtCommentEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CmtCommentService {

    private final CmtCommentEntityService cmtCommentEntityService;

    public CmtCommentDto findByUserId(Long userId) {

        CmtComment cmtComment = cmtCommentEntityService.getByUserIdWithControl(userId);
        return CmtCommentMapper.INSTANCE.convertToCmtCommentDto(cmtComment);

    }


    public List<CmtCommentDto> findAllByProductId(Long productId) {

        List<CmtComment> cmtCommentList = cmtCommentEntityService.getAllByProductId(productId);
        return CmtCommentMapper.INSTANCE.convertToCmtCommentDtoList(cmtCommentList);

    }


    public CmtCommentDto save(CmtCommentDto cmtCommentDto) {
        CmtComment cmtComment = CmtCommentMapper.INSTANCE.convertToCmtComment(cmtCommentDto);
        cmtComment = cmtCommentEntityService.save(cmtComment);
        return CmtCommentMapper.INSTANCE.convertToCmtCommentDto(cmtComment);
    }

    public void delete(Long id) {
        cmtCommentEntityService.delete(id);
    }
}
