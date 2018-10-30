

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class TesteJFrame {
	public void camera() throws InterruptedException {

		JLabel rotuloDigitarQRCodeLabel = new JLabel("Digite para gerar QR CODE:");
		JTextField digitarQRCodeTextField = new JTextField();
		JButton gerarQRButton = new JButton("Gerar QR CODE");
		JLabel rotuloVerConteudoQRCodeLabel = new JLabel("Veja o conteúdo do QR CODE");
		JLabel verConteudoQRCodeLabel = new JLabel();
		JButton obterDadosDoQRButton = new JButton("Ler QR CODE");
		JPanel demaisComponentesPanel = new JPanel(new GridLayout(7, 1));

		demaisComponentesPanel.add(rotuloDigitarQRCodeLabel);
		demaisComponentesPanel.add(digitarQRCodeTextField);
		demaisComponentesPanel.add(gerarQRButton);
		demaisComponentesPanel.add(rotuloVerConteudoQRCodeLabel);
		demaisComponentesPanel.add(verConteudoQRCodeLabel);
		demaisComponentesPanel.add(obterDadosDoQRButton);

		// pega uma referência para a webcam
		Webcam webcam = Webcam.getDefault();
		// configura o tamanho da tela a ser considerada
		webcam.setViewSize(WebcamResolution.VGA.getSize());
		// cria um painel para mostrar
		// WebcamPanel é subclasse de JPanel
		WebcamPanel webcamPanel = new WebcamPanel(webcam);
		// configura para exibir frames per second
		webcamPanel.setFPSDisplayed(true);
		// configura para mostrar informações de debug
		// provavemente você vai querer desabilitar quando a
		// aplicação estiver pronta :)
		webcamPanel.setDisplayDebugInfo(true);
		// configura para mostrar o tamanho da imagem
		webcamPanel.setImageSizeDisplayed(true);
		// configura para espelhar
		webcamPanel.setMirrored(true);

		obterDadosDoQRButton.addActionListener((e) -> {
			if (webcam.isOpen()) {// se a webcam está aberta
				BufferedImage foto = null;
				if ((foto = webcam.getImage()) != null) { // se tirou a foto
					LuminanceSource source = new BufferedImageLuminanceSource(foto);
					BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

					try {
						Result result = new MultiFormatReader().decode(bitmap);
						if (result != null) {
							verConteudoQRCodeLabel.setText(result.getText());
						}
					} catch (NotFoundException nfe) {
						// ignorar, nenhum qr code encontrado
						verConteudoQRCodeLabel.setText("QR Code não encontrado");
					}
				}

			}
		});

		// isso é uma expressão lambda, recurso novo do Java 8
		gerarQRButton.addActionListener((e) -> {
			try {
				String conteudo = digitarQRCodeTextField.getText();
				String nomeDoDiretorio = "qrcodes";
				File diretorio = new File (nomeDoDiretorio);
				if (!diretorio.exists()) {
					diretorio.mkdir();
				}
				int cont = diretorio.list().length; //pega qtos qrCodes já existem para usar no nome
				String nomeFigura = String.format("qrcode_%d.png", cont);
				String conteudoFormatado = new String (conteudo.getBytes("UTF-8"), "UTF-8");
				
				Map <EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap <> ();
				hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
				//monta a matriz de bits
				BitMatrix matrix = new MultiFormatWriter().encode(conteudoFormatado,
						BarcodeFormat.QR_CODE, 200, 200, hintMap);
				MatrixToImageWriter.writeToStream(matrix, "png", 
						new BufferedOutputStream(new FileOutputStream(
								new File (diretorio, nomeFigura))));
			}
			catch (UnsupportedEncodingException uee) {
				JOptionPane.showMessageDialog(null, "Encoding inválido");
			}
			catch (WriterException we) {
				JOptionPane.showMessageDialog(null, "writer exception: " + we.getMessage());
			}
			catch (IOException ioe) {
				JOptionPane.showMessageDialog(null, "io exception: " + ioe.getMessage());
			}
			
		});

		// cria o frame para adicionar o panel
		JFrame frame = new JFrame("Testando");
		Container container = frame.getContentPane();
		container.setLayout(new BorderLayout());
		container.add(webcamPanel, BorderLayout.WEST);
		container.add(demaisComponentesPanel, BorderLayout.EAST);
		frame.setResizable(true);
		frame.pack();
		frame.setVisible(true);
	}
}
