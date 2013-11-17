package com.lovo.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lovo.dao.ICommonDao;
import com.lovo.pojo.Base;

@Repository
public class CommonDao extends BaseDao implements ICommonDao<Base> {

	@Override
	public int insert(String namespace, Base entity) {
		return (Integer) this.getSqlMapClientTemplate().insert(namespace, entity);
	}

	@Override
	public void updateByField(String namespace, Object fieldName) {
		this.getSqlMapClientTemplate().update(namespace, fieldName);
	}

	@Override
	public void updateByMap(String namespace, Map<String, Object> map) {
		this.getSqlMapClientTemplate().update(namespace, map);
	}

	@Override
	public void updateByEntity(String namespace, Base entity) {
		this.getSqlMapClientTemplate().update(namespace, entity);
	}

	@Override
	public void deleteByField(String namespace, Object fieldName) {
		this.getSqlMapClientTemplate().delete(namespace, fieldName);
	}

	@Override
	public void deleteByMap(String namespace, Map<String, Object> map) {
		this.getSqlMapClientTemplate().delete(namespace, map);
	}

	@Override
	public void deleteByEntity(String namespace, Base entity) {
		this.getSqlMapClientTemplate().delete(namespace, entity);
	}

	@Override
	public Base findByField(String namespace, Object fieldName) {
		return (Base) this.getSqlMapClientTemplate().queryForObject(namespace, fieldName);
	}

	@Override
	public Base findByMap(String namespace, Map<String, Object> map) {
		return (Base) this.getSqlMapClientTemplate().queryForObject(namespace, map);
	}

	@Override
	public Base findByEntity(String namespace, Base entity) {
		return (Base) this.getSqlMapClientTemplate().queryForObject(namespace, entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Base> findByFieldList(String namespace, Object fieldName) {
		return this.getSqlMapClientTemplate().queryForList(namespace, fieldName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Base> findByMapList(String namespace, Map<String, Object> map) {
		return this.getSqlMapClientTemplate().queryForList(namespace, map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Base> findByEntityList(String namespace, Base entity) {
		return this.getSqlMapClientTemplate().queryForList(namespace, entity);
	}

	@Override
	public Object findByFieldObject(String namespace, Object fieldName) {
		return this.getSqlMapClientTemplate().queryForObject(namespace, fieldName);
	}

	@Override
	public Object findByMapObject(String namespace, Map<String, Object> map) {
		return this.getSqlMapClientTemplate().queryForObject(namespace, map);
	}

	@Override
	public Object findByEntityObject(String namespace, Base entity) {
		return this.getSqlMapClientTemplate().queryForObject(namespace, entity);
	}

}
