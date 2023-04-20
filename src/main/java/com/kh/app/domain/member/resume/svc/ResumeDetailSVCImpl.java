package com.kh.app.domain.member.resume.svc;

import com.kh.app.domain.member.resume.ResumeDetail;
import com.kh.app.domain.member.resume.dao.ResumeDetailDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ResumeDetailSVCImpl implements ResumeDetailSVC {

  private final ResumeDetailDAO resumeDetailDAO;


  @Override
  public void detailWrite(List<ResumeDetail> resumeDetails) {
    resumeDetailDAO.create(resumeDetails);
  }

  @Override
  public List<ResumeDetail> findByResumeDetailId(Long resumeId) {
    List<ResumeDetail> resumeDetails = resumeDetailDAO.selectOne(resumeId);
    return resumeDetails;
  }

  @Override
  public void detailModify(List<ResumeDetail> resumeDetails) {
  resumeDetailDAO.update(resumeDetails);
  }


  @Override
  public int detailRemove(Long resumeId) {
    return resumeDetailDAO.delete(resumeId);
  }
}
