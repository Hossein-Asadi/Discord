public class DiscordServer implements Runnable{
    @Override
    public void run() {
        System.out.println("Discord successfully is run");
        MainServer mainServer = new MainServer();
        System.out.println("Running Main Server");
        mainServer.run();
    }
}
