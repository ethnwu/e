/**
 * @author gupt1342
 * @version 10-11-2025
 */

public class Movie implements MovieInterface {
    private String title;
    private Time time;
    private double duration;
    private double rating;

    public Movie(String title, Time time, double duration, double rating) {
        if (title == null || title.isEmpty() || time == null || rating < 0 || rating > 10) {
            System.out.println("Invalid title or time");
            return;
        } else {
            try {
                Double.parseDouble(String.valueOf(duration));
            } catch (NumberFormatException e) {
                System.out.println("Invalid Duration");
                return;
            }
        }
        if (duration <= 0) {
            System.out.println("Invalid Duration");
            return;
        }
        this.title = title;
        this.time = time;
        this.duration = duration;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            System.out.println("Invalid title");
        } else {
            this.title = title;
        }
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        if (time == null) {
            System.out.println("Invalid time");
        } else {
            this.time = time;
        }
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        if (duration < 0) {
            System.out.println("Invalid duration");
        } else {
            this.duration = duration;
        }
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        if (rating < 0 || rating > 10) {
            System.out.println("Invalid rating");
        }  else {
            this.rating = rating;
        }
    }
}