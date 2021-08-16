package ir.dotprint.digiato.Model;

public class NewsItems {
    String id;
    String title;
    String shortdescription;
    String fulldescription;
    String smallpic;
    String pic;
    String date;
    String authorname;
    String authorpic;
    String category;


    public NewsItems(String id, String title, String shortdescription, String fulldescription, String smallpic, String pic, String date, String authorname, String authorpic, String category) {
        this.id = id;
        this.title = title;
        this.shortdescription = shortdescription;
        this.fulldescription = fulldescription;
        this.smallpic = smallpic;
        this.pic = pic;
        this.date = date;
        this.authorname = authorname;
        this.authorpic = authorpic;
        this.category = category;
    }


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

    public String getShortdescription() {
        return shortdescription;
    }

    public void setShortdescription(String shortdescription) {
        this.shortdescription = shortdescription;
    }

    public String getFulldescription() {
        return fulldescription;
    }

    public void setFulldescription(String fulldescription) {
        this.fulldescription = fulldescription;
    }

    public String getSmallpic() {
        return smallpic;
    }

    public void setSmallpic(String smallpic) {
        this.smallpic = smallpic;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public String getAuthorpic() {
        return authorpic;
    }

    public void setAuthorpic(String authorpic) {
        this.authorpic = authorpic;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
