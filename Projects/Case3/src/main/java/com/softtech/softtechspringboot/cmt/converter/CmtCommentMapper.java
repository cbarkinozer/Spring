package com.softtech.softtechspringboot.cmt.converter;

import com.softtech.softtechspringboot.cmt.dto.CmtCommentDto;
import com.softtech.softtechspringboot.cmt.entity.CmtComment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CmtCommentMapper {

    CmtCommentMapper INSTANCE = Mappers.getMapper(CmtCommentMapper.class);

    CmtComment convertToCmtComment(CmtCommentDto cmtCommentDto);
    List<CmtCommentDto> convertToCmtCommentDtoList(List<CmtComment> cmtCommentList);
    CmtCommentDto convertToCmtCommentDto(CmtComment cmtComment);

}
