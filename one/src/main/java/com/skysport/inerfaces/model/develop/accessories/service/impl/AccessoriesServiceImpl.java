package com.skysport.inerfaces.model.develop.accessories.service.impl;

import com.skysport.core.annotation.SystemServiceLog;
import com.skysport.core.model.common.impl.CommonServiceImpl;
import com.skysport.core.model.seqno.service.IncrementNumberService;
import com.skysport.core.utils.UuidGeneratorUtils;
import com.skysport.inerfaces.bean.develop.AccessoriesInfo;
import com.skysport.inerfaces.bean.develop.BomInfo;
import com.skysport.inerfaces.bean.develop.MaterialSpInfo;
import com.skysport.inerfaces.bean.develop.join.AccessoriesJoinInfo;
import com.skysport.inerfaces.mapper.info.AccessoriesManageMapper;
import com.skysport.inerfaces.model.develop.accessories.service.IAccessoriesService;
import com.skysport.inerfaces.model.develop.pantone.helper.KFMaterialPantoneServiceHelper;
import com.skysport.inerfaces.model.develop.pantone.service.IKFMaterialPantoneService;
import com.skysport.inerfaces.model.develop.position.helper.KFMaterialPositionServiceHelper;
import com.skysport.inerfaces.model.develop.position.service.IKFMaterialPositionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 说明:
 * Created by zhangjh on 2015/9/22.
 */
@Service("accessoriesService")
public class AccessoriesServiceImpl extends CommonServiceImpl<AccessoriesInfo> implements IAccessoriesService, InitializingBean {

    @Resource(name = "accessoriesManageMapper")
    private AccessoriesManageMapper accessoriesManageMapper;


    @Resource(name = "incrementNumber")
    private IncrementNumberService incrementNumberService;

    @Resource(name = "kFMaterialPositionService")
    private IKFMaterialPositionService kFMaterialPositionService;
    @Resource(name = "kFMaterialPantoneService")
    private IKFMaterialPantoneService kFMaterialPantoneService;

    @Override
    public void afterPropertiesSet() throws Exception {
        commonDao = accessoriesManageMapper;
    }

    /**
     * 保存辅料信息
     *
     * @param accessoriesItems
     * @param bomInfo
     */
    @Override
    @SystemServiceLog(description = "保存辅料信息")
    public List<AccessoriesInfo> updateOrAddBatch(List<AccessoriesJoinInfo> accessoriesItems, BomInfo bomInfo) {
        //返回辅料的id和序号信息
        List<AccessoriesInfo> accessoriesRtn = new ArrayList<>();

        //找出被删除的面料id，并删除
        String bomId = StringUtils.isBlank(bomInfo.getNatrualkey()) ? bomInfo.getBomId() : bomInfo.getNatrualkey();

        deleteAccessoriesByIds(accessoriesItems, bomId);

        if (null != accessoriesItems) {
            //面料id存在，修改；面料id不存在则新增
            for (AccessoriesJoinInfo accessoriesJoinInfo : accessoriesItems) {
                String accessoriesId = accessoriesJoinInfo.getAccessoriesInfo().getAccessoriesId();

                MaterialSpInfo materialSpInfo = accessoriesJoinInfo.getMaterialSpInfo();
                if (null != materialSpInfo.getUnitPrice() && null != materialSpInfo.getTotalAmount()) {
                    BigDecimal totalPrice = materialSpInfo.getUnitPrice().multiply(materialSpInfo.getTotalAmount());
                    materialSpInfo.setTotalPrice(totalPrice);
                }
                //有id，更新
                if (StringUtils.isNotBlank(accessoriesId)) {

                    setAccessoriesId(accessoriesJoinInfo, accessoriesId, bomId);
                    accessoriesManageMapper.updateInfo(accessoriesJoinInfo.getAccessoriesInfo());
                    accessoriesManageMapper.updateDosage(accessoriesJoinInfo.getMaterialUnitDosage());
                    accessoriesManageMapper.updateSp(accessoriesJoinInfo.getMaterialSpInfo());

                }
                //无id，新增
                else {
//                    String kind_name = buildKindName(bomInfo, accessoriesJoinInfo);
//
//                    String seqNo = BuildSeqNoHelper.SINGLETONE.getFullSeqNo(kind_name, incrementNumberService, WebConstants.MATERIAL_SEQ_NO_LENGTH);
//
//                    //年份+客户+地域+系列+NNN
//                    accessoriesId = kind_name + seqNo;
                    accessoriesId = UuidGeneratorUtils.getNextId();
                    setAccessoriesId(accessoriesJoinInfo, accessoriesId, bomId);
                    accessoriesManageMapper.add(accessoriesJoinInfo.getAccessoriesInfo());
                    //新增面料用量
                    accessoriesManageMapper.addDosage(accessoriesJoinInfo.getMaterialUnitDosage());
                    //新增面料供应商信息
                    accessoriesManageMapper.addSp(accessoriesJoinInfo.getMaterialSpInfo());

                }
                accessoriesRtn.add(accessoriesJoinInfo.getAccessoriesInfo());

                if (null != accessoriesJoinInfo.getAccessoriesInfo().getPositionIds() && !accessoriesJoinInfo.getAccessoriesInfo().getPositionIds().isEmpty()) {
                    kFMaterialPositionService.addBatch(accessoriesJoinInfo.getAccessoriesInfo().getPositionIds());
                }

                //保留物料颜色信息
                if (null != accessoriesJoinInfo.getAccessoriesInfo().getPantoneIds() && !accessoriesJoinInfo.getAccessoriesInfo().getPantoneIds().isEmpty()) {
                    kFMaterialPantoneService.addBatch(accessoriesJoinInfo.getAccessoriesInfo().getPantoneIds());
                }
            }
        }
        return accessoriesRtn;
    }
    @SystemServiceLog(description = "通过bomid查询辅料信息")
    @Override
    public List<AccessoriesInfo> queryAccessoriesList(String bomId) {
        return accessoriesManageMapper.queryAccessoriesList(bomId);
    }
    @SystemServiceLog(description = "通过bomid查询辅料信息")
    @Override
    public List<AccessoriesInfo> queryAllAccessoriesByBomId(String bomId) {
        return accessoriesManageMapper.queryAllAccessoriesByBomId(bomId);
    }

//    private String buildKindName(BomInfo bomInfo, AccessoriesJoinInfo accessoriesJoinInfo) {
//
//        String kind_name = CharConstant.EMPTY;
//
//        StringBuilder stringBuilder = new StringBuilder();
//        String materialTypeId = StringUtils.isBlank(accessoriesJoinInfo.getAccessoriesInfo().getMaterialTypeId()) ? WebConstants.ACCESSORIE_MATERIAL_TYPE_ID : accessoriesJoinInfo.getAccessoriesInfo().getMaterialTypeId();
//        stringBuilder.append(materialTypeId);
//        stringBuilder.append(accessoriesJoinInfo.getAccessoriesInfo().getSpId());
//        stringBuilder.append(accessoriesJoinInfo.getAccessoriesInfo().getYearCode());
//        stringBuilder.append(accessoriesJoinInfo.getAccessoriesInfo().getProductTypeId());
//
//        return kind_name;
//    }


    private void setAccessoriesId(AccessoriesJoinInfo accessoriesJoinInfo, String accessoriesId, String bomId) {

        accessoriesJoinInfo.getAccessoriesInfo().setAccessoriesId(accessoriesId);
        accessoriesJoinInfo.getAccessoriesInfo().setNatrualkey(accessoriesId);
        accessoriesJoinInfo.getAccessoriesInfo().setBomId(bomId);
        accessoriesJoinInfo.getMaterialUnitDosage().setMaterialId(accessoriesId);
        accessoriesJoinInfo.getMaterialSpInfo().setMaterialId(accessoriesId);
        //设置物料位置的物料id
        KFMaterialPositionServiceHelper.SINGLETONE.setPositionFabricId(accessoriesJoinInfo.getAccessoriesInfo().getPositionIds(), accessoriesId);
        //设置物料颜色的物料id
        KFMaterialPantoneServiceHelper.SINGLETONE.setPantoneFabricId(accessoriesJoinInfo.getAccessoriesInfo().getPantoneIds(), accessoriesId);
    }

    /**
     * 从数据库删除‘已从页面删除的辅料信息’
     *
     * @param accessoriesItems
     * @param bomId
     */
    private void deleteAccessoriesByIds(List<AccessoriesJoinInfo> accessoriesItems, String bomId) {

        List<String> allAccessoriesIds = accessoriesManageMapper.selectAllAccessoriesId(bomId);
        List<String> needToSaveAccessoriesId = buildNeedSaveAccessoriesId(accessoriesItems);
        allAccessoriesIds.removeAll(needToSaveAccessoriesId);
        if (null != allAccessoriesIds && !allAccessoriesIds.isEmpty()) {
            accessoriesManageMapper.deleteAccessoriesByIds(allAccessoriesIds);
        }


    }

    private List<String> buildNeedSaveAccessoriesId(List<AccessoriesJoinInfo> accessoriesItems) {

        List<String> needToSaveAccessoriesId = new ArrayList<String>();
        if (null != accessoriesItems) {
            for (AccessoriesJoinInfo accessoriesJoinInfo : accessoriesItems) {
                String accessoriesId = accessoriesJoinInfo.getAccessoriesInfo().getAccessoriesId();
                if (StringUtils.isNotEmpty(accessoriesId)) {
                    needToSaveAccessoriesId.add(accessoriesId);
                }
            }
        }
        return needToSaveAccessoriesId;
    }

}
