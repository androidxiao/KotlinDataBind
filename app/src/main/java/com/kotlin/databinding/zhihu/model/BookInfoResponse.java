package com.kotlin.databinding.zhihu.model;

import java.io.Serializable;
import java.util.List;


public class BookInfoResponse implements Serializable {
    /**
     * rating : {"max":10,"numRaters":5364,"average":"6.7","min":0}
     * subtitle :
     * isbn13 : 9787506339223
     * pubdate : 2007-3
     * author : ["王朔"]
     * origin_title :
     * price : 28.00元
     * publisher : 作家出版社
     * binding : 精装
     * id : 2030664
     * translator : []
     * series : {"id":"9905","title":"王朔文集"}
     * images : {"small":"https://img3.doubanio.com/spic/s2340010.jpg","large":"https://img3.doubanio.com/lpic/s2340010.jpg","medium":"https://img3.doubanio.com/mpic/s2340010.jpg"}
     * title : 我的千岁寒
     * summary : 这本书是王朔近年来的五部作品的合集，包括《我的千岁寒》、北京话版《金刚经》、《唯物论史纲》、《宫里的日子》以及剧本《梦想照进现实》的小说版、调侃性的影视评论《与孙甘露对话》。
     王朔在为全书做的序《我是谁》中，自称偏爱本来写给张元的《我的千岁寒》，并强调“这部作品让汉语有了时态”。《我的千岁寒》取材于《六祖坛经》，写的是主人公慧能悟道的传奇故事，慧能从一个不识字的樵夫，成为享誉青史的一代宗师，被西方人称作“东方耶稣”，这个独特的历史现象，很是令人回味！小说融入了作者自己的哲学思考和人生观。历经三版，王朔自信地说，这部作品“全是文字的精华，要说美文这叫美文，这可是给高级知识分子看的”。
     除了《我的千岁寒》，新书中还有充满“科学味”的北京话版《金刚经》第二版与《唯物论史纲》。北京话版《金刚经》用北京话通俗地“科学”地重写了《金刚经》。王朔表示，《金刚经》成书于两千年前，那时物理和化学没有现在发达，更多的得靠观心，既然物理都发展到这个地步了，就得拿起物理这个利器，这是把锃亮的刀子。“有的作家还从传统中找灵感，那就是传统的奴隶，你得从科学里找。”王朔说。
     新书中的《唯物论史纲》原来叫《论上帝是物质》，源自王朔给女儿考大学推荐的哲学题纲，后来他一“推”不可收拾，“发现物质后面还有人”，一路推演至今日。
     新书里还有《宫里的日子》，王朔自评这是“根据《资治通鉴》改编的小武的故事，不完全是史实，有些废太子李承乾的行举‘按’在高阳身上”。他还表明“《宫里的日子》是给老徐（徐静蕾）写的，希望今年能拍出来”。当然，新书也收入了他为徐静蕾写的电影《梦想照进现实》的小说版、调侃性的影视评论《与孙甘露对话》
     对于新书题材风格均与以往作品不同，王朔说：“我把过去自己的东西全部砸碎，这才能绝处逢生。我放眼的是宇宙。以前说，民族的是世界的，我说，个人的才是世界的。”
     * pages : 337
     */

    private RatingBean rating;
    private String subtitle;
    private String isbn13;
    private String pubdate;
    private String origin_title;
    private String price;
    private String publisher;
    private String binding;
    private String id;
    private SeriesBean series;
    private ImagesBean images;
    private String title;
    private String summary;
    private String pages;
    private List<String> author;
    private List<String> translator;

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getOrigin_title() {
        return origin_title;
    }

    public void setOrigin_title(String origin_title) {
        this.origin_title = origin_title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SeriesBean getSeries() {
        return series;
    }

    public void setSeries(SeriesBean series) {
        this.series = series;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public List<?> getTranslator() {
        return translator;
    }

    public void setTranslator(List<String> translator) {
        this.translator = translator;
    }

    public static class RatingBean {
        /**
         * max : 10
         * numRaters : 5364
         * average : 6.7
         * min : 0
         */

        private int max;
        private int numRaters;
        private String average;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getNumRaters() {
            return numRaters;
        }

        public void setNumRaters(int numRaters) {
            this.numRaters = numRaters;
        }

        public String getAverage() {
            return average;
        }

        public void setAverage(String average) {
            this.average = average;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    public static class SeriesBean {
        /**
         * id : 9905
         * title : 王朔文集
         */

        private String id;
        private String title;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class ImagesBean {
        /**
         * small : https://img3.doubanio.com/spic/s2340010.jpg
         * large : https://img3.doubanio.com/lpic/s2340010.jpg
         * medium : https://img3.doubanio.com/mpic/s2340010.jpg
         */

        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }

}
