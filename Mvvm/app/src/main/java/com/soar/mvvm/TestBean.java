package com.soar.mvvm;

import com.soar.mvvmlib.net.bean.BaseBean;

import java.util.List;

/**
 * Created by gaofei on 2016/11/25.
 */
public class TestBean extends BaseBean{
//    { "status":true,"total":1048,"tngu":[{"count":44,"description":"这些病是“睡”出来的 很多人没意识到 ·肝病肝出问题的人同样容易感觉身体疲劳，常见症状有精神不济、乏力、恶心呕吐等，患者会出现嗜睡现象","fcount":0,"id":20775,"img":"/lore/161120/f52cba0d381032108cf5920151504a5e.jpeg","keywords":"解决办法 嗜睡 睡眠 引起 出现 ","loreclass":1,"rcount":0,"time":1479641951000,"title":"再迟钝的人也该意识到这是病了"},{"count":112,"description":"老人牙齿为何容易发酸？教你8个护齿方法 警惕：要排除口腔癌症2周以上不能愈合的口腔溃疡；口腔黏膜出现白色或红色斑点（斑块）；口腔出现非炎症性肿胀并伴有颈部的淋巴结肿大；口腔和颈部不明原因的麻木或疼痛；长期吸烟，大量饮酒和咀嚼槟榔的人在自查的基础上，定期接受口腔癌筛查","fcount":0,"id":20703,"img":"/lore/161109/3f62748780a3201ab9fa84069cecd29e.jpg","keywords":"牙齿 敏感 口腔 牙龈 咀嚼 ","loreclass":1,"rcount":0,"time":1478691720000,"title":"但是中老年人容易牙齿发酸"}]}

    public boolean status;
    public int total;

    public List<TianGou> tngou;

    public static class TianGou{
        public int count;
        public String description;
        public int fcount;
        public int id;
        public String img;
        public String keywords;
        public int loreclass;
        public int rcount;
        public long time;
        public String title;


        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getFcount() {
            return fcount;
        }

        public void setFcount(int fcount) {
            this.fcount = fcount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public int getLoreclass() {
            return loreclass;
        }

        public void setLoreclass(int loreclass) {
            this.loreclass = loreclass;
        }

        public int getRcount() {
            return rcount;
        }

        public void setRcount(int rcount) {
            this.rcount = rcount;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<TianGou> getTngou() {
        return tngou;
    }

    public void setTngou(List<TianGou> tngou) {
        this.tngou = tngou;
    }
}
