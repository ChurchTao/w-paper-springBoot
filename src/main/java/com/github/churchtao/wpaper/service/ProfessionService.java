package com.github.churchtao.wpaper.service;


import com.github.churchtao.wpaper.constant.StatusConst;
import com.github.churchtao.wpaper.dao.ProfessionRepository;
import com.github.churchtao.wpaper.dao.util.PageableTools;
import com.github.churchtao.wpaper.dto.PageObjectDTO;
import com.github.churchtao.wpaper.dto.TagDTO;
import com.github.churchtao.wpaper.dto.TypeDTO;
import com.github.churchtao.wpaper.entity.Profession;
import com.github.churchtao.wpaper.exception.ServerException;
import com.github.churchtao.wpaper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author taojiacheng
 * @create 2018-04-12 17:08
 **/
@Service
public class ProfessionService {
    @Autowired
    private ProfessionRepository professionDAO;

    public Profession addProfession(String name,String avatar,String about){
        if (professionDAO.getByName(name)!=null){
            throw new ServerException(500,"已经存在该模块了!");
        }
        Profession profession = new Profession();
        profession.setAvatar(StringUtil.isEmpty(avatar)?"http://p7mnquexm.bkt.clouddn.com/default.jpg":avatar);
        profession.setName(name);
        profession.setAbout(about);
        profession.setCreateTime(new Date());
        profession.setStatus(StatusConst.ON);
        return professionDAO.save(profession);
    }

    @Transactional(rollbackFor = ServerException.class)
    public int changeFocus(int userId,int tagId,int status){
        Map resultMap = professionDAO.selectCombine(userId,tagId);
        if (resultMap.get("status")==null){
            professionDAO.insertUserProfession(userId,tagId);
            return 1;
        }else {
            professionDAO.updateUserProfession(status,tagId,userId);
            return status;
        }
    }

    public Profession updateProfessionInfo(int professionId, String name, String avatar, String about, int status){
        Optional<Profession> profession = professionDAO.findById(professionId);
        if (!profession.isPresent()){
            throw new ServerException(404,"不存在该模块!");
        }
        Profession profession1 = profession.get();
        profession1.setStatus(status);
        profession1.setName(name);
        profession1.setUpdateTime(new Date());
        profession1.setAvatar(avatar);
        profession1.setAbout(about);
        return professionDAO.save(profession1);
    }
    public PageObjectDTO getTagsByUidFocus(int userId,int pageNum,int pageSize){
        Page<Profession> professions = professionDAO.findByUserFocus(userId, PageableTools.noSortPage(pageNum,pageSize));
        List<TagDTO> tags= new ArrayList<>(professions.getSize());
        professions.forEach(one->{
            TagDTO tag = new TagDTO(one.getId(),one.getAvatar(),one.getName(),one.getStarNum(),one.getPostNum(),true);
            tags.add(tag);
        });
        return PageObjectDTO.init(professions.getTotalElements(),professions.getTotalPages(),tags);
    }

    public PageObjectDTO getTagsByUidNotFocus(int userId,int pageNum,int pageSize){
        Page<Profession> professionsNotFocus = professionDAO.findProfessionByNotFocus(userId, PageableTools.noSortPage(pageNum,pageSize));
        List<TagDTO> tags= new ArrayList<>(professionsNotFocus.getSize());
        professionsNotFocus.forEach(one->{
            TagDTO tag = new TagDTO(one.getId(),one.getAvatar(),one.getName(),one.getStarNum(),one.getPostNum(),false);
            tags.add(tag);
        });
        return PageObjectDTO.init(professionsNotFocus.getTotalElements(),professionsNotFocus.getTotalPages(),tags);
    }

    public List<TypeDTO> getTypeByUid(int userId){
        List<Profession> professions=professionDAO.findAllByUserFocus(userId);
        List<TypeDTO> result = new ArrayList<TypeDTO>();
        result.add(new TypeDTO("/","推荐","",0));
        professions.forEach(one->{
            TypeDTO typeDTO = new TypeDTO("/welcome/"+one.getId(),one.getName(),String.valueOf(one.getId()),one.getId());
            result.add(typeDTO);
        });
        return result;
    }

    public List<TypeDTO> getTypes(){
        Page<Profession> professions = professionDAO.findAll(PageableTools.noSortPage(0,6));
        List<TypeDTO> result = new ArrayList<TypeDTO>();
        result.add(new TypeDTO("/","推荐","",0));
        professions.forEach(profession->{
            TypeDTO typeDTO = new TypeDTO("/welcome/"+profession.getId(),profession.getName(),String.valueOf(profession.getId()),profession.getId());
            result.add(typeDTO);
        });
        return result;
    }
}
