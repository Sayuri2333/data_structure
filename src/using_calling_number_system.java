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
        jb1 = new JButton("�Һ�");
        jb2 = new JButton("��ѯ");
        jb3 = new JButton("ҵ��");
        jb4 = new JButton("�˳�");
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
        this.setTitle("�Һ�ϵͳ");
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
        if (e.getActionCommand() == "�Һ�"){
            int number = system.add();
            JOptionPane.showMessageDialog(null,"��ȡ����ɹ�! �������ǵ�" + number + "��!","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
            try{
                FileOutputStream os = new FileOutputStream("D:\\system.data");
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(system);
                oos.flush();
                oos.close();
            } catch (IOException i){}
        }else if (e.getActionCommand() == "��ѯ"){
            int size = system.size();
            JOptionPane.showMessageDialog(null,"����һ����" + size + "����","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
            try{
                FileOutputStream os = new FileOutputStream("D:\\system.data");
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(system);
                oos.flush();
                oos.close();
            } catch (IOException i){}
        }else if (e.getActionCommand() == "ҵ��"){
            int out = system.poll();
            if (out != 0)
                JOptionPane.showMessageDialog(null,"��" + out + "���˿��Խ���ҵ�����!","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
            else
                JOptionPane.showMessageDialog(null,"��ǰ����û���˽����Ŷ�!","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
            try{
                FileOutputStream os = new FileOutputStream("D:\\system.data");
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(system);
                oos.flush();
                oos.close();
            } catch (IOException i){}
        }else if (e.getActionCommand() == "�˳�"){
            stop = true;
            try{
                FileOutputStream os = new FileOutputStream("D:\\system.data");
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(system);
                oos.flush();
                oos.close();
                JOptionPane.showMessageDialog(null,"�˳��ɹ�!","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            } catch (IOException i){}
        }
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        using_calling_number_system us = new using_calling_number_system();
    }
}
