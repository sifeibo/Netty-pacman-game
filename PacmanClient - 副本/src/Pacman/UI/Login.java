package Pacman.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import Pacman.message.Game;
import Pacman.service.Client;


public class Login extends JFrame implements ActionListener {
    private JPanel pan = new JPanel();
    private JLabel namelab = new JLabel("用户名");
    private JLabel passlab = new JLabel("密  码");
    private JTextField nametext = new JTextField();
    //    private JPasswordField passtext = new JPasswordField();
    private JTextField passtext = new JTextField();


    public JButton denglu = new JButton("登录");
    public JButton zhuce = new JButton("注册");


    public  Login() {

        Font font = new Font("黑体", Font.PLAIN, 20);
        super.setTitle("登  录");
        pan.setLayout(null);
        pan.setBackground(new Color(222, 237, 189));

        namelab.setBounds(20, 50, 60, 50);
        nametext.setBounds(150, 50, 250, 50);
        passlab.setBounds(20, 120, 60, 50);
        passtext.setBounds(150, 120, 250, 50);
        denglu.setBounds(90, 250, 150, 50);
        zhuce.setBounds(250, 250, 150, 50);


        pan.add(namelab);
        pan.add(nametext);
        pan.add(passlab);
        pan.add(passtext);
        pan.add(denglu);
        pan.add(zhuce);


        namelab.setFont(font);
        passlab.setFont(font);
        nametext.setFont(font);
        passtext.setFont(font);
        denglu.setFont(font);
        zhuce.setFont(font);


        denglu.addActionListener(this);
        zhuce.addActionListener(this);


        super.add(pan);
        super.setSize(500, 500);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent arg0) {
        if(arg0.getSource()==denglu){
            denglu();
        }else if (arg0.getSource()==zhuce) {
            zhuce();
        }
    }

    //登录按钮的事件处理函数
    public void denglu(){
        String username = nametext.getText();
        String password = passtext.getText();
        Game a = new Game(username, password, 1,0, false);
        Client.sendLoginObject(a);
    }


    //注册按钮触发后的事件处理函数
    public void zhuce(){
        String username = nametext.getText();
        String password = passtext.getText();
        Game a = new Game(username, password, 2,0, false);
        Client.sendLoginObject(a);
    }

}
