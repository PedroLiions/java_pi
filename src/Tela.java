import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.google.gson.Gson;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Tela extends JFrame implements ActionListener {
	//Componentes
	private Container cnt;
	private  BufferedImage buffImage;
	private JPanel pnlHeader, pnlSideButtons, pnlCnh, pnlETitulo, pnlCtps;
	private JTabbedPane tpType;
	private JButton btnReadWebcam, btnReadFile, btnCriar_cnh, btnCriar_et, btnCriar_ctps;
	private JTextField txtCpf_cnh, txtRegister_cnh, txtFirstName_cnh, txtLastName_cnh, txtEmissionAt_cnh,
						txtCpf_et, txtRegister_et, txtFirstName_et, txtLastName_et,txtEmissionAt_et,
						txtCpf_ctps, txtRegister_ctps, txtFirstName_ctps, txtLastName_ctps, txtEmissionAt_ctps;
	private JLabel lblHeader,
					lblCpf_cnh, lblRegister_cnh, lblEmissionAt_cnh, lblFirstName_cnh, lblLastName_cnh,
					lblCpf_et, lblRegister_et, lblEmissionAt_et, lblFirstName_et, lblLastName_et,
					lblCpf_ctps, lblRegister_ctps, lblEmissionAt_ctps, lblFirstName_ctps, lblLastName_ctps;
	
	public Tela() {
		//Titulo do formulario
		super("QR Coder");
		
		//Instanciando Container
		cnt = getContentPane();
		
		//Instanciando JPanels
		pnlHeader = new JPanel();
		pnlSideButtons = new JPanel();
		pnlCnh = new JPanel();
		pnlETitulo = new JPanel();
		pnlCtps = new JPanel();
		
		//Instanciando JTabbedPane
		tpType = new JTabbedPane();
		
		//Instanciando JButtons
		btnReadWebcam = new JButton();
		btnReadFile = new JButton();
		btnCriar_cnh = new JButton();
		btnCriar_et = new JButton();
		btnCriar_ctps = new JButton();
		
		//Instanciando JTextFields
		txtCpf_cnh = new JTextField("", 20);
		txtCpf_et = new JTextField("", 20);
		txtCpf_ctps = new JTextField("", 20);
		txtRegister_cnh = new JTextField("", 20);
		txtRegister_et = new JTextField("", 20);
		txtRegister_ctps = new JTextField("", 20);
		txtFirstName_cnh = new JTextField("", 20);
		txtFirstName_et = new JTextField("", 20);
		txtFirstName_ctps = new JTextField("", 20);
		txtLastName_cnh = new JTextField("", 20);
		txtLastName_et = new JTextField("", 20);
		txtLastName_ctps = new JTextField("", 20);
		txtEmissionAt_cnh = new JTextField("", 20);
		txtEmissionAt_et = new JTextField("", 20);
		txtEmissionAt_ctps = new JTextField("", 20);
		
		//Instanciando JLabels
		lblHeader = new JLabel("Leitor de Documentos Digitais");
		lblCpf_cnh = new JLabel("CPF:");
		lblCpf_et = new JLabel("CPF:");
		lblCpf_ctps = new JLabel("CPF:");
		lblRegister_cnh = new JLabel("RG:");
		lblRegister_et = new JLabel("RG:");
		lblRegister_ctps = new JLabel("RG:");
		lblFirstName_cnh = new JLabel("Nome:");
		lblFirstName_et = new JLabel("Nome:");
		lblFirstName_ctps = new JLabel("Nome:");
		lblLastName_cnh = new JLabel("Sobrenome:");
		lblLastName_et = new JLabel("Sobrenome:");
		lblLastName_ctps = new JLabel("Sobrenome:");
		lblEmissionAt_cnh = new JLabel("Data de Emissao:");
		lblEmissionAt_et = new JLabel("Data de Emissao:");
		lblEmissionAt_ctps = new JLabel("Data de Emissao:");
		
		
		//Definindo layout
		cnt.setLayout(new BorderLayout());
		pnlHeader.setLayout(new FlowLayout());
		pnlSideButtons.setLayout(new FlowLayout());
		pnlCnh.setLayout(new FlowLayout());
		pnlETitulo.setLayout(new FlowLayout());
		pnlCtps.setLayout(new FlowLayout());
		
		//Adicionando conteudo aos paineis	
		
		//pnlHeader
		pnlHeader.add(lblHeader);
		
		//pnlSideButtons
		pnlSideButtons.add(btnReadWebcam);
		pnlSideButtons.add(btnReadFile);
		
		//pnlCnh
		pnlCnh.add(lblFirstName_cnh);
		pnlCnh.add(txtFirstName_cnh);
		pnlCnh.add(lblLastName_cnh);
		pnlCnh.add(txtLastName_cnh);
		pnlCnh.add(lblCpf_cnh);
		pnlCnh.add(txtCpf_cnh);
		pnlCnh.add(lblEmissionAt_cnh);
		pnlCnh.add(txtEmissionAt_cnh);
		pnlCnh.add(btnCriar_cnh);
		
		//pnlETitulo
		pnlETitulo.add(lblFirstName_et);
		pnlETitulo.add(txtFirstName_et);
		pnlETitulo.add(lblLastName_et);
		pnlETitulo.add(txtLastName_et);
		pnlETitulo.add(lblCpf_et);
		pnlETitulo.add(txtCpf_et);
		pnlETitulo.add(lblEmissionAt_et);
		pnlETitulo.add(txtEmissionAt_et);
		pnlETitulo.add(btnCriar_et);
		
		//pnlCtps
		pnlCtps.add(lblFirstName_ctps);
		pnlCtps.add(txtFirstName_ctps);
		pnlCtps.add(lblLastName_ctps);
		pnlCtps.add(txtLastName_ctps);
		pnlCtps.add(lblCpf_ctps);
		pnlCtps.add(txtCpf_ctps);
		pnlCtps.add(lblEmissionAt_ctps);
		pnlCtps.add(txtEmissionAt_ctps);
		pnlCtps.add(btnCriar_ctps);

		//tpType
		tpType.add("CNH", pnlCnh);
		tpType.add("E-Titulo", pnlETitulo);
		tpType.add("CTPS", pnlCtps);
		
		//cnt
		cnt.add(BorderLayout.NORTH, pnlHeader);
		cnt.add(BorderLayout.EAST, pnlSideButtons);
		cnt.add(BorderLayout.CENTER, tpType);
		
		
		//Ajustes de lbl
		lblHeader.setFont(new Font("Arial", Font.BOLD, 28));
		
		//Ajustes de btn
		btnReadWebcam.setText("Ler QR Code\n(Webcam)");
		btnReadWebcam.setSize(150, 100);
		
		btnReadFile.setText("Ler QR Code\n(Arquivo)");
		btnReadFile.setSize(150, 100);
		
		btnCriar_cnh.setText("Criar CNH");
		btnCriar_cnh.setSize(150, 100);
		
		btnCriar_et.setText("Criar E-Titulo");
		btnCriar_et.setSize(150, 100);
		
		btnCriar_ctps.setText("Criar CTPS");
		btnCriar_ctps.setSize(150, 100);
		
		//Ajustes de tp
		//tpType.setSize();
		
		//Ajustes de pnl
		pnlHeader.setBackground(Color.DARK_GRAY);
		lblHeader.setForeground(Color.WHITE);
		pnlSideButtons.setSize(200, 500);
		pnlCnh.setSize(800,600);
		
		//Ajustes de txt
		
		
		//action
		btnReadFile.addActionListener(this);
		btnReadWebcam.addActionListener(this);
		btnCriar_cnh.addActionListener(this);
		btnCriar_et.addActionListener(this);
		btnCriar_ctps.addActionListener(this);
		
		//Ajustes de tela
		setSize(1024,640); //Tamanho inicial
		setLocationRelativeTo(null); //Aparecer no centro da tela
		setDefaultCloseOperation(Tela.EXIT_ON_CLOSE); //Sair ao fechar
		setVisible(true); //Visibilidade
		setResizable(false); //Tela nao reajustavel
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnReadFile){
	         int resultado = 0;
	        
			 
             JFileChooser fChooser = new JFileChooser();
              fChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	               
              if (fChooser.showSaveDialog(this) != 1){
                   File fImage = fChooser.getSelectedFile();
                   try {
                        buffImage = ImageIO.read(fImage);
                   } catch (IOException evet) {
                        // TODO Auto-generated catch block
                        evet.printStackTrace();
                   }
              }
                       
                 
              LuminanceSource source = new BufferedImageLuminanceSource(buffImage);  
              BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));  
              MultiFormatReader reader = new MultiFormatReader();  
              com.google.zxing.Result result;
				try {
					result = reader.decode(bitmap);
					resultado = Integer.parseInt(result.getText());
				} catch (NotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}  

			 Cnh cnh = new Cnh();
	         
			 cnh.conectaInterface(resultado);
			 //TODO IMPLEMENTS
			 
	         txtCpf_cnh.setText(cnh.getCpf_cnh());
	         txtEmissionAt_cnh.setText(cnh.getEmissionAt_cnh());
	         txtRegister_cnh.setText(cnh.getRegister_cnh());
	         txtFirstName_cnh.setText(cnh.getFirstName_cnh());
	         txtLastName_cnh.setText(cnh.getLastName_cnh());
		}
		if (e.getSource() == btnReadWebcam) {
			TesteJFrame teste = new TesteJFrame();
			try {
				teste.camera();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == btnCriar_cnh) {
			//obtém os dados dos campos visuais
			String cpf = txtCpf_cnh.getText();
			String register = txtRegister_cnh.getText();
			String firstName = txtFirstName_cnh.getText();
			String lastName = txtLastName_cnh.getText();
			String emissionAt = txtEmissionAt_cnh.getText();
			
			//constrói os objetos
			Documento u = new Documento (cpf, register, firstName, lastName, emissionAt);
			//agora vamos usar a biblioteca GSON
			Gson gson = new Gson();
			String documentoEmFormatoJSON = gson.toJson(u);
			System.out.println(documentoEmFormatoJSON);
			
			new Thread () {
				public void run() {
				OkHttpClient client = new OkHttpClient();
				MediaType MimeType = MediaType.parse("application/json;charset=utf-8");
				RequestBody body = RequestBody.create(MimeType, documentoEmFormatoJSON);
				Request request = new Request.Builder().url
				("https://whispering-hollows-24920.herokuapp.com/descriptografar").post(body).build();
				try {
				//ainda não estamos fazendo nada com a resposta
				Response response =
				client.newCall(request).execute();
				}
				catch (IOException e) {
				e.printStackTrace();
				}
				}
				}.start();
		}
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable () {
			@Override
			public void run() {
			new Tela();
			}
		});	
	}
}
