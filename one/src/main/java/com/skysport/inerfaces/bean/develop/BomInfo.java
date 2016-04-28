package com.skysport.inerfaces.bean.develop;import com.skysport.core.bean.system.SelectItem;import com.skysport.inerfaces.bean.develop.join.AccessoriesJoinInfo;import com.skysport.inerfaces.bean.develop.join.FabricsJoinInfo;import com.skysport.inerfaces.bean.develop.join.KFPackagingJoinInfo;import java.util.List;/** * */public class BomInfo extends SelectItem {    private String id;    /**     * 项目编号     */    private String projectId;    /**     * 项目编号     */    private String projectName;    /**     *     */    private String bomId;    /**     *     */    private String bomName;    /**     * 客户     */    private String customerId;    /**     * 区域     */    private String areaId;    /**     * 系列     */    private String seriesId;    /**     * 系列     */    private String seriesName;    /**     * 款式     */    private String collectionNum;    /**     * 主颜色     */    private String mainColor;    /**     * 主颜色     */    private String mainColorOld;    /**     * 面料交货时间     */    private String fabricsEndDate;    /**     * 辅料交货时间     */    private String accessoriesEndDate;    /**     * 成衣报价时间     */    private String preOfferDate;    /**     * 成衣收到时间     */    private String clothReceivedDate;    /**     * 订单数量     */    private int offerAmount;    private String approveStatus;    /**     * 所有面料     */    private List<FabricsInfo> fabrics;    /**     * 所有面料     */    private List<FabricsJoinInfo> fabricItems;    /**     * 所有辅料     */    private List<AccessoriesInfo> accessories;    /**     * 所有辅料     */    private List<AccessoriesJoinInfo> accessoriesItems;    /**     * 包装材料     */    private List<PackagingInfo> packagings;    private List<KFPackagingJoinInfo> packagingItems;    /**     * 成衣厂     */    private List<FactoryQuoteInfo> factoryQuoteInfos;    /**     * 报价表     */    private QuotedInfo quotedInfo;    /**     * 生产指示单     */    private KfProductionInstructionEntity productionInstruction;    private int delFlag;    private String updateTime;    private String remark;    private String sexId;    public KfProductionInstructionEntity getProductionInstruction() {        return productionInstruction;    }    public void setProductionInstruction(KfProductionInstructionEntity productionInstruction) {        this.productionInstruction = productionInstruction;    }    public String getSeriesName() {        return seriesName;    }    public void setSeriesName(String seriesName) {        this.seriesName = seriesName;    }    public String getProjectName() {        return projectName;    }    public void setProjectName(String projectName) {        this.projectName = projectName;    }    @Override    public String getId() {        return id;    }    @Override    public void setId(String id) {        this.id = id;    }    @Override    public int getDelFlag() {        return delFlag;    }    @Override    public void setDelFlag(int delFlag) {        this.delFlag = delFlag;    }    @Override    public String getUpdateTime() {        return updateTime;    }    @Override    public void setUpdateTime(String updateTime) {        this.updateTime = updateTime;    }    @Override    public String getRemark() {        return remark;    }    @Override    public void setRemark(String remark) {        this.remark = remark;    }    public String getProjectId() {        return projectId;    }    public void setProjectId(String projectId) {        this.projectId = projectId;    }    public String getBomId() {        return bomId;    }    public void setBomId(String bomId) {        this.bomId = bomId;    }    public String getBomName() {        return bomName;    }    public void setBomName(String bomName) {        this.bomName = bomName;    }    public String getCustomerId() {        return customerId;    }    public void setCustomerId(String customerId) {        this.customerId = customerId;    }    public String getAreaId() {        return areaId;    }    public void setAreaId(String areaId) {        this.areaId = areaId;    }    public String getSeriesId() {        return seriesId;    }    public void setSeriesId(String seriesId) {        this.seriesId = seriesId;    }    public String getMainColor() {        return mainColor;    }    public void setMainColor(String mainColor) {        this.mainColor = mainColor;    }    public String getFabricsEndDate() {        return fabricsEndDate;    }    public void setFabricsEndDate(String fabricsEndDate) {        this.fabricsEndDate = fabricsEndDate;    }    public String getAccessoriesEndDate() {        return accessoriesEndDate;    }    public void setAccessoriesEndDate(String accessoriesEndDate) {        this.accessoriesEndDate = accessoriesEndDate;    }    public String getCollectionNum() {        return collectionNum;    }    public void setCollectionNum(String collectionNum) {        this.collectionNum = collectionNum;    }    public String getPreOfferDate() {        return preOfferDate;    }    public void setPreOfferDate(String preOfferDate) {        this.preOfferDate = preOfferDate;    }    public String getClothReceivedDate() {        return clothReceivedDate;    }    public void setClothReceivedDate(String clothReceivedDate) {        this.clothReceivedDate = clothReceivedDate;    }    public int getOfferAmount() {        return offerAmount;    }    public void setOfferAmount(int offerAmount) {        this.offerAmount = offerAmount;    }    public List<FabricsInfo> getFabrics() {        return fabrics;    }    public void setFabrics(List<FabricsInfo> fabrics) {        this.fabrics = fabrics;    }    public List<FabricsJoinInfo> getFabricItems() {        return fabricItems;    }    public void setFabricItems(List<FabricsJoinInfo> fabricItems) {        this.fabricItems = fabricItems;    }    public String getSexId() {        return sexId;    }    public void setSexId(String sexId) {        this.sexId = sexId;    }    public QuotedInfo getQuotedInfo() {        return quotedInfo;    }    public void setQuotedInfo(QuotedInfo quotedInfo) {        this.quotedInfo = quotedInfo;    }    public List<AccessoriesJoinInfo> getAccessoriesItems() {        return accessoriesItems;    }    public void setAccessoriesItems(List<AccessoriesJoinInfo> accessoriesItems) {        this.accessoriesItems = accessoriesItems;    }    public List<AccessoriesInfo> getAccessories() {        return accessories;    }    public void setAccessories(List<AccessoriesInfo> accessories) {        this.accessories = accessories;    }    public List<PackagingInfo> getPackagings() {        return packagings;    }    public void setPackagings(List<PackagingInfo> packagings) {        this.packagings = packagings;    }    public List<KFPackagingJoinInfo> getPackagingItems() {        return packagingItems;    }    public void setPackagingItems(List<KFPackagingJoinInfo> packagingItems) {        this.packagingItems = packagingItems;    }    public List<FactoryQuoteInfo> getFactoryQuoteInfos() {        return factoryQuoteInfos;    }    public void setFactoryQuoteInfos(List<FactoryQuoteInfo> factoryQuoteInfos) {        this.factoryQuoteInfos = factoryQuoteInfos;    }    public String getMainColorOld() {        return mainColorOld;    }    public void setMainColorOld(String mainColorOld) {        this.mainColorOld = mainColorOld;    }    public String getApproveStatus() {        return approveStatus;    }    public void setApproveStatus(String approveStatus) {        this.approveStatus = approveStatus;    }    @Override    public String toString() {        return "BomInfo{" +                "id='" + id + '\'' +                ", projectId='" + projectId + '\'' +                ", projectName='" + projectName + '\'' +                ", bomId='" + bomId + '\'' +                ", bomName='" + bomName + '\'' +                ", customerId='" + customerId + '\'' +                ", areaId='" + areaId + '\'' +                ", seriesId='" + seriesId + '\'' +                ", seriesName='" + seriesName + '\'' +                ", collectionNum='" + collectionNum + '\'' +                ", mainColor='" + mainColor + '\'' +                ", mainColorOld='" + mainColorOld + '\'' +                ", fabricsEndDate='" + fabricsEndDate + '\'' +                ", accessoriesEndDate='" + accessoriesEndDate + '\'' +                ", preOfferDate='" + preOfferDate + '\'' +                ", clothReceivedDate='" + clothReceivedDate + '\'' +                ", offerAmount=" + offerAmount +                ", approveStatus='" + approveStatus + '\'' +                ", fabrics=" + fabrics +                ", fabricItems=" + fabricItems +                ", accessories=" + accessories +                ", accessoriesItems=" + accessoriesItems +                ", packagings=" + packagings +                ", packagingItems=" + packagingItems +                ", factoryQuoteInfos=" + factoryQuoteInfos +                ", quotedInfo=" + quotedInfo +                ", delFlag=" + delFlag +                ", updateTime='" + updateTime + '\'' +                ", remark='" + remark + '\'' +                ", sexId='" + sexId + '\'' +                '}';    }}