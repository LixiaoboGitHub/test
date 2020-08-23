package com.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.po.EmpWelfare;
import com.po.Welfare;

@Service("EmpWelfareDAO")
public interface IEmpWelfareMapper {
   //保存员工福利数据
	public int save(EmpWelfare ewf);
   //根据员工编号，删除福利数据
	public int delByEid(Integer eid);
   //根据员工编号查找员工对应的福利数据
	public List<Welfare> findByEid(Integer eid);
}
