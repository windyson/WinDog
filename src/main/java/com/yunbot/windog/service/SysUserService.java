package com.yunbot.windog.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.yunbot.windog.common.base.BaseService;
import com.yunbot.windog.common.support.ConvertUtil;
import com.yunbot.windog.mapper.auto.TSysRoleUserMapper;
import com.yunbot.windog.mapper.auto.TsysRoleMapper;
import com.yunbot.windog.mapper.auto.TsysUserMapper;
import com.yunbot.windog.mapper.custom.RoleDao;
import com.yunbot.windog.model.auto.TSysRoleUser;
import com.yunbot.windog.model.auto.TSysRoleUserExample;
import com.yunbot.windog.model.auto.TsysRole;
import com.yunbot.windog.model.auto.TsysRoleExample;
import com.yunbot.windog.model.auto.TsysUser;
import com.yunbot.windog.model.auto.TsysUserExample;
import com.yunbot.windog.model.custom.RoleVo;
import com.yunbot.windog.model.custom.Tablepar;
import com.yunbot.windog.util.SnowflakeIdWorker;
import com.yunbot.windog.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import static com.yunbot.windog.controller.security.SM3Utils.generateSM3HASH;

/**
 * 系统用户
 *
 * @author fuce
 * @ClassName: SysUserService
 * @date 2018年8月26日
 */
@Service
public class SysUserService implements BaseService<TsysUser, TsysUserExample> {
	//生成的用户dao
	@Autowired
	private TsysUserMapper tsysUserMapper;
	
	//生成的角色用户dao
	@Autowired
	private TSysRoleUserMapper tSysRoleUserMapper;
	
	//自定义角色dao
	@Autowired
	private RoleDao roleDao;
	
	//自动生成的角色dao
	@Autowired
	private TsysRoleMapper tsysRoleMapper;
	
	/**
	 * 分页查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	 public PageInfo<TsysUser> list(Tablepar tablepar,String username){
	        TsysUserExample testExample=new TsysUserExample();
	        testExample.setOrderByClause("id+0 DESC");
	        if(username!=null&&!"".equals(username)){
	        	testExample.createCriteria().andUsernameLike("%"+username+"%");
	        }

	        PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
	        List<TsysUser> list= tsysUserMapper.selectByExample(testExample);
	        PageInfo<TsysUser> pageInfo = new PageInfo<TsysUser>(list);
	        return  pageInfo;
	 }

	
	@Override
	public int deleteByPrimaryKey(String ids) {
		List<String> lista=ConvertUtil.toListStrArray(ids);
		TsysUserExample example=new TsysUserExample();
		example.createCriteria().andIdIn(lista);
		return tsysUserMapper.deleteByExample(example);
	}
	
	/**
	 * 添加用户
	 */
	@Override
	public int insertSelective(TsysUser record) {
		return tsysUserMapper.insertSelective(record);
	}
	
	/**
	 * 添加用户跟角色信息
	 * @param record
	 * @param roles
	 * @return
	 */
	@Transactional
	public int insertUserRoles(TsysUser record,List<String> roles) {
		String userid = SnowflakeIdWorker.getUUID();
		record.setId(userid);
		if (StringUtils.isNotEmpty(roles)) {
			for (String rolesid : roles) {
				TSysRoleUser roleUser = new TSysRoleUser(SnowflakeIdWorker.getUUID(), userid, rolesid);
				tSysRoleUserMapper.insertSelective(roleUser);
			}
		}

		//密码加密
		/**
		 * by william 2021-4-26
		 * 替换成国密sm3的加密验证，密码变成64个加密字符了
		 * 原生代码采用md5的算法，该代码主要是实现用sm3替代md5
		 * */
		//record.setPassword(MD5Util.encode(record.getPassword()));
		record.setPassword(generateSM3HASH(record.getPassword()));
		return tsysUserMapper.insertSelective(record);
	}
	
	@Override
	public TsysUser selectByPrimaryKey(String id) {
		
		return tsysUserMapper.selectByPrimaryKey(id);
	}

	
	@Override
	public int updateByPrimaryKeySelective(TsysUser record) {
		/**
		 * by william 2021-4-26
		 * 替换成国密sm3的加密验证，密码变成64个加密字符了
		 * 原生代码采用md5的算法，该代码主要是实现用sm3替代md5
		 * */
//		record.setPassword(MD5Util.encode(record.getPassword()));
		record.setPassword(generateSM3HASH(record.getPassword()));
		return tsysUserMapper.updateByPrimaryKeySelective(record);
	}
	
	
	

	
	@Override
	public int updateByExampleSelective(TsysUser record, TsysUserExample example) {
		
		return tsysUserMapper.updateByExampleSelective(record, example);
	}

	
	@Override
	public int updateByExample(TsysUser record, TsysUserExample example) {
		
		return tsysUserMapper.updateByExample(record, example);
	}

	@Override
	public List<TsysUser> selectByExample(TsysUserExample example) {
		
		return tsysUserMapper.selectByExample(example);
	}

	
	@Override
	public long countByExample(TsysUserExample example) {
		
		return tsysUserMapper.countByExample(example);
	}

	
	@Override
	public int deleteByExample(TsysUserExample example) {
		
		return tsysUserMapper.deleteByExample(example);
	}
	
	/**
	 * 检查用户name
	 * @param tsysUser
	 * @return
	 */
	public int checkLoginNameUnique(TsysUser tsysUser){
		TsysUserExample example=new TsysUserExample();
		example.createCriteria().andUsernameEqualTo(tsysUser.getUsername());
		List<TsysUser> list=tsysUserMapper.selectByExample(example);
		
		return list.size();
	}
	
	/**
	 * 获取所有权限 并且增加是否有权限字段
	 * @return
	 */
	public List<RoleVo> getUserIsRole(String userid){
		List<RoleVo> list=new ArrayList<RoleVo>();
		//查询出我的权限
		List<TsysRole> myRoles= roleDao.queryUserRole(userid);
		TsysRoleExample tsysRoleExample=new TsysRoleExample();
		//查询系统所有的角色
		List<TsysRole> tsysRoles=tsysRoleMapper.selectByExample(tsysRoleExample);
		if(StringUtils.isNotEmpty(tsysRoles)){
			for (TsysRole tsysRole : tsysRoles) {
				Boolean isflag=false;
				RoleVo roleVo=new RoleVo(tsysRole.getId(),tsysRole.getName(), isflag);
				for (TsysRole myRole : myRoles) {
					if(tsysRole.getId().equals(myRole.getId())){
						isflag=true;
						break;
					}
				}
				if(isflag){
					roleVo.setIscheck(true);
					list.add(roleVo);
				}else{
					list.add(roleVo);
				}
			}
		}
		return list;
	}
	
	
	/**
	 * 修改用户密码
	 * @param record
	 * @return
	 */
	public int updateUserPassword(TsysUser record) {
		/**
		 * by william 2021-4-26
		 * 替换成国密sm3的加密验证，密码变成64个加密字符了
		 * 原生代码采用md5的算法，该代码主要是实现用sm3替代md5
		 * */
//		record.setPassword(MD5Util.encode(record.getPassword()));
		record.setPassword(generateSM3HASH(record.getPassword()));
		//修改用户信息
		return tsysUserMapper.updateByPrimaryKeySelective(record);
	}
	
	
	/**
	 * 修改用户信息以及角色信息
	 * @param record
	 * @param roles
	 * @return
	 */
	@Transactional
	public int updateUserRoles(TsysUser record,List<String> roles) {
		
		
		
		//先删除这个用户的所有角色
		TSysRoleUserExample tSysRoleUserExample=new TSysRoleUserExample();
		tSysRoleUserExample.createCriteria().andSysUserIdEqualTo(record.getId());
		tSysRoleUserMapper.deleteByExample(tSysRoleUserExample);
		if(roles!=null) {
			//添加新的角色信息
			for (String role : roles) {
				TSysRoleUser tSysRoleUser= new TSysRoleUser(SnowflakeIdWorker.getUUID(), record.getId(), role);
				tSysRoleUserMapper.insertSelective(tSysRoleUser);
			}
		}
		//修改用户信息
		return tsysUserMapper.updateByPrimaryKeySelective(record);
	}
	
	
	
}
