package com.xz.base.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.xz.base.domain.BaseEntity;
import com.xz.base.model.RelationModel;

public class Cnd<T extends BaseEntity> {
private T t;
	
	public Cnd(T e){
		t = e;
		if(t.getRelationModelList()==null){
			t.setRelationModelList(new ArrayList<RelationModel>());
		}
		t.setCnd_type("custom");
	}
	
	public T fetchEntity(){
		return t;
	}
	
	public void clear(){
		t = null;
	}
	
	public void resetEntity(T f){
		t = f;
		if(t.getRelationModelList()==null){
			t.setRelationModelList(new ArrayList<RelationModel>());
		}
		t.setCnd_type("custom");
	}
	
	public void originalEntity(){
		t.getRelationModelList().clear();
		t.setCnd_type("default");
	}
	
	public Cnd<T> eq(String field,Object value,String... args){
		for(RelationModel tempRelationModel:t.getRelationModelList()){
			if(field.equals(tempRelationModel.getField_name())){
				t.getRelationModelList().remove(tempRelationModel);
				break;
			}
		}
		String sql_type = null;
		if(args.length>0){
			sql_type = args[0];
		}else{
			sql_type = "AND";
		}
		t.getRelationModelList().add(new RelationModel(field,"=",value,sql_type,null));
		return this;
	}
	
	public Cnd<T> lt(String field,Object value,String... args){
		for(RelationModel tempRelationModel:t.getRelationModelList()){
			if(field.equals(tempRelationModel.getField_name())){
				t.getRelationModelList().remove(tempRelationModel);
				break;
			}
		}
		String sql_type = null;
		if(args.length>0){
			sql_type = args[0];
		}else{
			sql_type = "AND";
		}
		t.getRelationModelList().add(new RelationModel(field,"<",value,sql_type,null));
		return this;
	}
	
	public Cnd<T> lte(String field,Object value,String... args){
		for(RelationModel tempRelationModel:t.getRelationModelList()){
			if(field.equals(tempRelationModel.getField_name())){
				t.getRelationModelList().remove(tempRelationModel);
				break;
			}
		}
		String sql_type = null;
		if(args.length>0){
			sql_type = args[0];
		}else{
			sql_type = "AND";
		}
		t.getRelationModelList().add(new RelationModel(field,"<=",value,sql_type,null));
		return this;
	}
	
	public Cnd<T> gt(String field,Object value,String... args){
		for(RelationModel tempRelationModel:t.getRelationModelList()){
			if(field.equals(tempRelationModel.getField_name())){
				t.getRelationModelList().remove(tempRelationModel);
				break;
			}
		}
		String sql_type = null;
		if(args.length>0){
			sql_type = args[0];
		}else{
			sql_type = "AND";
		}
		t.getRelationModelList().add(new RelationModel(field,">",value,sql_type,null));
		return this;
	}
	
	public Cnd<T> gte(String field,Object value,String... args){
		for(RelationModel tempRelationModel:t.getRelationModelList()){
			if(field.equals(tempRelationModel.getField_name())){
				t.getRelationModelList().remove(tempRelationModel);
				break;
			}
		}
		String sql_type = null;
		if(args.length>0){
			sql_type = args[0];
		}else{
			sql_type = "AND";
		}
		t.getRelationModelList().add(new RelationModel(field,">=",value,sql_type,null));
		return this;
	}
	
	public Cnd<T> preLike(String field,String value,String... args){
		for(RelationModel tempRelationModel:t.getRelationModelList()){
			if(field.equals(tempRelationModel.getField_name())){
				t.getRelationModelList().remove(tempRelationModel);
				break;
			}
		}
		String sql_type = null;
		if(args.length>0){
			sql_type = args[0];
		}else{
			sql_type = "AND";
		}
		t.getRelationModelList().add(new RelationModel(field,"like",value,sql_type,"pre"));
		return this;
	}
	
	public Cnd<T> sufLike(String field,String value,String... args){
		for(RelationModel tempRelationModel:t.getRelationModelList()){
			if(field.equals(tempRelationModel.getField_name())){
				t.getRelationModelList().remove(tempRelationModel);
				break;
			}
		}
		String sql_type = null;
		if(args.length>0){
			sql_type = args[0];
		}else{
			sql_type = "AND";
		}
		t.getRelationModelList().add(new RelationModel(field,"like",value,sql_type,"suf"));
		return this;
	}
	
	public Cnd<T> bothLike(String field,String value,String... args){
		for(RelationModel tempRelationModel:t.getRelationModelList()){
			if(field.equals(tempRelationModel.getField_name())){
				t.getRelationModelList().remove(tempRelationModel);
				break;
			}
		}
		String sql_type = null;
		if(args.length>0){
			sql_type = args[0];
		}else{
			sql_type = "AND";
		}
		t.getRelationModelList().add(new RelationModel(field,"like",value,sql_type,"both"));
		return this;
	}
	
	public Cnd<T> in(String field,List<String> value,String... args){
		for(RelationModel tempRelationModel:t.getRelationModelList()){
			if(field.equals(tempRelationModel.getField_name())){
				t.getRelationModelList().remove(tempRelationModel);
				break;
			}
		}
		String sql_type = null;
		if(args.length>0){
			sql_type = args[0];
		}else{
			sql_type = "AND";
		}
		t.getRelationModelList().add(new RelationModel(field,"in","("+StringUtils.join(value.toArray(), ",")+")",sql_type,null));
		return this;
	}
	
	public Cnd<T> copy(){
		t.setCnd_type("custom");
		t.getRelationModelList().clear();
		Field[] fields = t.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {  
            fields[i].setAccessible(true);  
            // 字段名  
            System.out.print(fields[i].getName() + ",");  
            // 字段值  
            if (fields[i].getType().getName().equals(  
                    java.lang.String.class.getName())) {  
                // String type  
                try {  
                    System.out.print(fields[i].get(t));  
                } catch (IllegalArgumentException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                } catch (IllegalAccessException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                }  
            } else if (fields[i].getType().getName().equals(  
                    java.lang.Integer.class.getName())  
                    || fields[i].getType().getName().equals("int")) {  
                // Integer type  
                try {  
                    System.out.println(fields[i].getInt(t));  
                } catch (IllegalArgumentException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                } catch (IllegalAccessException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                }  
            }  
            // 其他类型。。。  
        }
		return this;
	}
	
	
	public Cnd<T> lteXy(String field,Object value,String... args){
		
		String sql_type = null;
		if(args.length>0){
			sql_type = args[0];
		}else{
			sql_type = "AND";
		}
		t.getRelationModelList().add(new RelationModel(field,"<=",value,sql_type,null));
		return this;
	}
	
	public Cnd<T> gtDy(String field,Object value,String... args){
		
		String sql_type = null;
		if(args.length>0){
			sql_type = args[0];
		}else{
			sql_type = "AND";
		}
		t.getRelationModelList().add(new RelationModel(field,">",value,sql_type,null));
		return this;
	}
	
}
