package frames;

public class Application {
    private String usuario;
    protected Application application;
    protected MainFrame mainFrame;

    public static void main(String[] args) {
        new Application();
    }

    public Application(){
        mainFrame = new MainFrame();
        mainFrame.setVisible(true);
        application = this;
        LoginFrame loginGUI = new LoginFrame(application, mainFrame);
        loginGUI.getJDialog().setVisible(true);

        // aguarda o o termino do login
        while(loginGUI.getJDialog().isShowing()){}

        mainFrame.exibe(true);
    }
}
