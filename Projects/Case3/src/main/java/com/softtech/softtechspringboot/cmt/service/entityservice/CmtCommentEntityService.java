package com.softtech.softtechspringboot.cmt.service.entityservice;

import com.softtech.softtechspringboot.cmt.dao.CmtCommentDao;
import com.softtech.softtechspringboot.cmt.entity.CmtComment;
import com.softtech.softtechspringboot.cmt.enums.CmtErrorMessage;
import com.softtech.softtechspringboot.gen.exceptions.IllegalFieldException;
import com.softtech.softtechspringboot.gen.exceptions.ItemNotFoundException;
import com.softtech.softtechspringboot.gen.service.BaseEntityService;
import com.softtech.softtechspringboot.prd.service.entityservice.PrdProductEntityService;
import com.softtech.softtechspringboot.usr.service.entityservice.UsrUserEntityService;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CmtCommentEntityService extends BaseEntityService<CmtComment, CmtCommentDao> {

    private final CmtCommentDao cmtCommentDao;
    private final UsrUserEntityService usrUserEntityService;
    private final PrdProductEntityService prdProductEntityService;

    public CmtCommentEntityService(CmtCommentDao cmtCommentDao,
                                   UsrUserEntityService usrUserEntityService,
                                   PrdProductEntityService prdProductEntityService) {
        super(cmtCommentDao);
        this.cmtCommentDao = cmtCommentDao;
        this.usrUserEntityService=usrUserEntityService;
        this.prdProductEntityService = prdProductEntityService;
    }

    public CmtComment getByUserIdWithControl(Long userId) {

        return checkIfCommentExistsByUserId(userId);

    }

    private CmtComment checkIfCommentExistsByUserId(Long userId) {

        CmtComment cmtComment = cmtCommentDao.findByUserId(userId)
                .orElseThrow(() -> new ItemNotFoundException(
                        usrUserEntityService.findByIdWithControl(userId).getUsername(),
                        CmtErrorMessage.USER_HAS_NO_COMMENT) );

        return cmtComment;
    }


    public List<CmtComment> getAllByProductId(Long productId) {

        List<CmtComment> cmtCommentList = cmtCommentDao.findAllByProductId(productId);
        if(cmtCommentList.isEmpty()){
            throw new ItemNotFoundException(
                    prdProductEntityService.findById(productId).getName(),
                    CmtErrorMessage.PRODUCT_HAS_NO_COMMENT);
        }else{
            return cmtCommentList;
        }

    }

    public CmtComment save(CmtComment cmtComment) {
        controlIsCommentBlank(cmtComment);
        cmtCommentDao.save(cmtComment);
        return cmtComment;
    }

    private void controlIsCommentBlank(CmtComment cmtComment){
        if(cmtComment.getComment().isBlank()){
            throw new IllegalFieldException(CmtErrorMessage.COMMENT_IS_BLANK);
        }
    }

    public void delete(Long id){

        CmtComment cmtComment = checkIfCommentExistsById(id);
        cmtCommentDao.delete(cmtComment);

    }

    private CmtComment checkIfCommentExistsById(Long id){

        CmtComment cmtComment = cmtCommentDao.findById(id)
                .orElseThrow(()-> new ItemNotFoundException(CmtErrorMessage.COMMENT_NOT_FOUND));

        return cmtComment;

    }

}
