package com.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz.IEmpBiz;
import com.po.Emp;
import com.po.EmpWelfare;
import com.po.PageBean;
import com.po.Salary;
import com.po.Welfare;
import com.service.DaoService;
@Service("EmpBiz")
@Transactional
public class EmpBiz implements IEmpBiz {
	@Resource(name="DaoService")
	   private DaoService daoservice;
	
	public DaoService getDaoservice() {
		return daoservice;
	}

	public void setDaoservice(DaoService daoservice) {
		this.daoservice = daoservice;
	}

	@Override
	public boolean save(Emp emp) {
		int code=daoservice.getEmpmapper().save(emp);
		//因为在添加员工时，还要添加薪资和福利，需要用到员工Id
		if(code>0){
			//获取员工最大编号的方法
			int eid=daoservice.getEmpmapper().findMaxEid();
			/**保存薪资**/
			Salary sa=new Salary(eid,emp.getEmoney());
			daoservice.getSalarymapper().save(sa);
			/**保存薪资end**/
			/**获取员工福利id数组**/
			String[] wids=emp.getWids();
			if(wids!=null&&wids.length>0){
				for (int i = 0; i < wids.length; i++) {
					EmpWelfare ewf=new EmpWelfare(eid,new Integer(wids[i]));
					daoservice.getEmpwelfaremapper().save(ewf);
				}
			}
			/**获取员工福利id数组end**/
			return true;
		}
		return false;
	}

	@Override
	public boolean update(Emp emp) {
		int code=daoservice.getEmpmapper().update(emp);
		if(code>0){
			/**更新薪资**/
			 //获取该员工的原有薪资
			Salary oldsalary=daoservice.getSalarymapper().findSalaryByEid(emp.getEid());
			if(oldsalary!=null&&oldsalary.getEmoney()!=null){
				oldsalary.setEmoney(emp.getEmoney());//薪资改动
				daoservice.getSalarymapper().updateByEid(oldsalary);
			}else{
				Salary sa=new Salary(emp.getEid(),emp.getEmoney());
				daoservice.getSalarymapper().save(sa);
			}
			/**更新薪资end**/
			/**更新福利**/
			 //获取原来的福利
			List<Welfare> lswf=daoservice.getEmpwelfaremapper().findByEid(emp.getEid());
			if(lswf!=null&&lswf.size()>0){
				//删除原有福利
				daoservice.getEmpwelfaremapper().delByEid(emp.getEid());
			}
			//添加新的福利
			String[] wids=emp.getWids();
			if(wids!=null&&wids.length>0){
			   for (int i = 0; i < wids.length; i++) {
				   EmpWelfare ewf=new EmpWelfare(emp.getEid(),new Integer(wids[i]));
				   daoservice.getEmpwelfaremapper().save(ewf);
				}	
			}
			
			/**更新福利end**/
			return true;
		}
		return false;
	}

	@Override
	public boolean delById(Integer eid) {
		//先删除从表数据
		daoservice.getSalarymapper().delByEid(eid);
		daoservice.getEmpwelfaremapper().delByEid(eid);
		//删除员工数据
		int code=daoservice.getEmpmapper().delById(eid);
		if(code>0){
			return true;
		}
		return false;
	}

	@Override
	public Emp findById(Integer eid) {
		//获取员工对象
		Emp emp=daoservice.getEmpmapper().findById(eid);
		/**获取薪资添加到emp对象**/
		Salary sa=daoservice.getSalarymapper().findSalaryByEid(eid);
		if(sa!=null&&sa.getEmoney()!=null){
			emp.setEmoney(sa.getEmoney());
		}
		/**获取薪资添加到emp对象end**/
		/**获取福利添加到emp对象**/
		  //根据员工编号获取福利集合
		List<Welfare> lswf=daoservice.getEmpwelfaremapper().findByEid(eid);
		if(lswf!=null&&lswf.size()>0){
			//创建福利id数组
			String[] wids=new String[lswf.size()];
			for (int i = 0; i < wids.length; i++) {
				Welfare wf=lswf.get(i);
				wids[i]=wf.getWid().toString();
			}
			emp.setWids(wids);
		}
		emp.setLswf(lswf);
		/**获取福利添加到emp对象end**/
		return emp;
	}

	@Override
	public List<Emp> findPageAll(PageBean pb) {
		if(pb==null){
			pb=new PageBean();
		}
		return daoservice.getEmpmapper().findPageAll(pb);
	}

	@Override
	public int findMaxRow() {
		// TODO Auto-generated method stub
		return daoservice.getEmpmapper().findmaxRows();
	}

}
