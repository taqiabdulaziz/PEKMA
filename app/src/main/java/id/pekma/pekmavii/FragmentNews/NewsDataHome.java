package id.pekma.pekmavii.FragmentNews;

public class NewsDataHome extends NewsData {

    public String newsImage = "https://api.androidhive.info/json/movies/1.jpg";
    public String fishName;
    public String desc;
    public String deschome;
    public String title;
    public int price;

    public String getDeschome() {
        return deschome;
    }

    public void setDeschome(String deschome) {
        this.deschome = deschome;
    }

    public String getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(String newsImage) {
        this.newsImage = newsImage;
    }

    public String getFishName() {
        return fishName;
    }

    public void setFishName(String fishName) {
        this.fishName = fishName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
