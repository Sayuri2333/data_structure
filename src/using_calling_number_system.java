import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class using_calling_number_system  extends JFrame implements ActionListener{
    JPanel jp1;
    JButton jb1, jb2, jb3, jb4;
    public using_calling_number_system(){
        jp1 = new JPanel();
        jb1 = new JButton("挂号");
        jb2 = new JButton("查询");
        jb3 = new JButton("业务");
        jb4 = new JButton("退出");
        jb1.addActionListener(this::actionPerformed);
        jb2.addActionListener(this::actionPerformed);
        jb3.addActionListener(this::actionPerformed);
        jb4.addActionListener(this::actionPerformed);
        this.setLayout(new GridLayout(3, 1));
        jp1.add(jb1);
        jp1.add(jb2);
        jp1.add(jb3);
        jp1.add(jb4);
        this.add(jp1);
        this.setTitle("挂号系统");
        this.setSize(300, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        boolean stop = false;
        calling_number_system system = new calling_number_system();
        File file = new File("D:\\system.data");
        if (file.exists()){
            try{
                FileInputStream is = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(is);
                system = (calling_number_system)ois.readObject();
                ois.close();
            }catch (IOException  | ClassNotFoundException q){}
        }
        if (e.getActionCommand() == "挂号"){
            int number = system.add();
            JOptionPane.showMessageDialog(null,"领取号码成功! 您现在是第" + number + "号!","提示消息",JOptionPane.WARNING_MESSAGE);
            try{
                FileOutputStream os = new FileOutputStream("D:\\system.data");
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(system);
                oos.flush();
                oos.close();
            } catch (IOException i){}
        }else if (e.getActionCommand() == "查询"){
            int size = system.size();
            JOptionPane.showMessageDialog(null,"队列一共有" + size + "个人","提示消息",JOptionPane.WARNING_MESSAGE);
            try{
                FileOutputStream os = new FileOutputStream("D:\\system.data");
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(system);
                oos.flush();
                oos.close();
            } catch (IOException i){}
        }else if (e.getActionCommand() == "业务"){
            int out = system.poll();
            if (out != 0)
                JOptionPane.showMessageDialog(null,"第" + out + "号人可以进行业务办理!","提示消息",JOptionPane.WARNING_MESSAGE);
            else
                JOptionPane.showMessageDialog(null,"当前队列没有人进行排队!","提示消息",JOptionPane.WARNING_MESSAGE);
            try{
                FileOutputStream os = new FileOutputStream("D:\\system.data");
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(system);
                oos.flush();
                oos.close();
            } catch (IOException i){}
        }else if (e.getActionCommand() == "退出"){
            stop = true;
            try{
                FileOutputStream os = new FileOutputStream("D:\\system.data");
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(system);
                oos.flush();
                oos.close();
                JOptionPane.showMessageDialog(null,"退出成功!","提示消息",JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            } catch (IOException i){}
        }
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        using_calling_number_system us = new using_calling_number_system();
    }
}
