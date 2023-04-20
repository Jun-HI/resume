package com.kh.app.domain.member.resume.dao;

import com.kh.app.domain.member.resume.ResumeDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ResumeDetailDAOImpl implements ResumeDetailDAO{

  private final JdbcTemplate jdbcTemplate;

  @Override
  public void create(List<ResumeDetail> resumeDetails) {

    //SQL작성
    StringBuffer sql = new StringBuffer();
    sql.append("insert into resume_detail(resume_detail_id, resume_id, resume_item_id,id_person, business_name, business_date, business_work) ");
    sql.append("values(resume_detail_resume_detail_id_seq.nextval, ?, ?, ?, ?, ?, ?)  ");

    //SQL실행
    jdbcTemplate.batchUpdate(
        sql.toString(),
        resumeDetails,
        10,
        (ps, resumeDetail)-> {
          ps.setLong(1, resumeDetail.getResumeId());
          ps.setLong(2, resumeDetail.getResumeItemId());
          ps.setString(3, resumeDetail.getIdPerson());
          ps.setString(4, resumeDetail.getBusinessName());
          ps.setLong(5, resumeDetail.getBusinessDate());
          ps.setString(6, resumeDetail.getBusinessWork());
        });
  }


  @Override
  public List<ResumeDetail> selectOne(Long resumeDetailId) {

    StringBuffer sql = new StringBuffer();
    sql.append("select resume_detail_id, resume_id, resume_item_id, id_person, business_name, business_date, business_work ");
    sql.append("  from resume_detail ");
    sql.append(" where resume_id = ? ");

    List<ResumeDetail> detailList = jdbcTemplate.query(
        sql.toString(), new BeanPropertyRowMapper<>(ResumeDetail.class), resumeDetailId);

    return detailList;
  }

  @Override
  public void update(List<ResumeDetail> resumeDetails) {
    log.info("dao_update,resumeDetails={}",resumeDetails);
    StringBuffer sql = new StringBuffer();
    sql.append("update resume_detail ");
    sql.append("set business_name = ?, ");
    sql.append("    business_date = ?, ");
    sql.append("    business_work = ? ");
    sql.append(" where resume_detail_id = ? ");


    jdbcTemplate.batchUpdate(
        sql.toString(),
        resumeDetails,
        10,
        (ps, resumeDetail) -> {
          ps.setString(1, resumeDetail.getBusinessName());
          ps.setLong(2, resumeDetail.getBusinessDate());
          ps.setString(3, resumeDetail.getBusinessWork());
          ps.setLong(4, resumeDetail.getResumeDetailId());
        });
  }
  @Override
  public int delete(Long resumeId) {

    StringBuffer sql = new StringBuffer();
    sql.append("delete from resume_detail ");
    sql.append(" where resume_id = ? ");

    int cnt = jdbcTemplate.update(sql.toString(), resumeId);


    return cnt;
  }
}