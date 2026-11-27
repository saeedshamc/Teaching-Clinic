// MediaSystem.java — پاسخ پروژهٔ جلسه ۲۱
public class MediaSystem {

    interface Playable {
        void play();
        void pause();
        void stop();
    }

    interface Recordable {
        void record();
    }

    static class AudioPlayer implements Playable {
        String track;
        boolean playing;

        AudioPlayer(String track) { this.track = track; }

        @Override public void play() {
            playing = true;
            System.out.println("Playing audio: " + track);
        }
        @Override public void pause() {
            if (playing) { playing = false; System.out.println("Paused at 00:30"); }
        }
        @Override public void stop() {
            playing = false;
            System.out.println("Stopped");
        }
    }

    static class VideoPlayer implements Playable, Recordable {
        String video;
        boolean playing;

        VideoPlayer(String video) { this.video = video; }

        @Override public void play() {
            playing = true;
            System.out.println("Playing video: " + video);
        }
        @Override public void pause() {
            if (playing) { playing = false; System.out.println("Paused video"); }
        }
        @Override public void stop() {
            playing = false;
            System.out.println("Video stopped");
        }
        @Override public void record() {
            System.out.println("Recording: " + video);
        }
    }

    // کنترلر که هر Playable را می‌گیرد
    static class MediaController {
        void play(Playable p) { p.play(); }
        void pause(Playable p) { p.pause(); }
        void stop(Playable p) { p.stop(); }
    }

    public static void main(String[] args) {
        MediaController mc = new MediaController();
        AudioPlayer audio = new AudioPlayer("Music.mp3");
        VideoPlayer video = new VideoPlayer("Movie.mp4");

        mc.play(audio);
        mc.pause(audio);
        mc.play(video);
        video.record();
        mc.stop(video);
    }
}
