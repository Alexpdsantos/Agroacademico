package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import Controller.BeneficiarControl;
import DAO.BeneficiarDAO;
import Model.BeneficiarModel;

public class Beneficiar extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTable table;
	private JTextField textEntrada;
	private JTextField textRendimento;
	public JTextField textDescricao;
	private JTextField textSaida;
	private JTextField textLote;
	private JTextField textProduto;
	private JTextField textVariedade;
	JDateChooser dataBeneficiar = new JDateChooser();
	int selected;
	int selectedRow;

	BeneficiarControl control = new BeneficiarControl();
	BeneficiarDAO bDAO = new BeneficiarDAO();

	BeneficiarModel bModel = new BeneficiarModel();
	private JTextField textIdProduto;
	private JTextField textId;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Beneficiar frame = new Beneficiar();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Beneficiar() {
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent arg0) {

				try {
					bDAO.deleteZero();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			public void windowLostFocus(WindowEvent arg0) {
			}
		});

		setTitle("Beneficiamento de Produto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 879, 637);
		setModal(true);

		JScrollPane scrollPane = new JScrollPane();

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, Color.BLACK, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addGap(8)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 845, Short.MAX_VALUE).addContainerGap())
				.addGroup(groupLayout.createSequentialGroup().addContainerGap(159, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 548, GroupLayout.PREFERRED_SIZE).addGap(156)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE).addGap(39)));

		JButton btnEncerrar = new JButton("Finalizar Beneficiamento");
		btnEncerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRow() != -1) {
					float saida1 = Float.parseFloat(textSaida.getText());
					float entrada1 = Float.parseFloat(textEntrada.getText());
					if (saida1 <= 0 | saida1 > entrada1) {
						JOptionPane.showMessageDialog(rootPane,
								"Por favor, informe um valor válido para pesos de Entrada e Saída!");
					} else {
						Date data = (Date) dataBeneficiar.getDate();
						String produto = textProduto.getText();
						String descricao = textDescricao.getText();
						String lote = textLote.getText();
						String talhao = textVariedade.getText();
						float entrada = Float.parseFloat(textEntrada.getText());
						float saida = Float.parseFloat(textSaida.getText());
						float rendimento = Float.parseFloat(textRendimento.getText());
						float valor = Float.parseFloat(textEntrada.getText());
						int idBeneficiar = Integer.parseInt(textId.getText());
						String idProduto = textIdProduto.getText();
						System.out.println(idProduto);

						try {
							if (control.finaliza(data, produto, descricao, lote, talhao, entrada, saida, rendimento,
									valor, idProduto, idBeneficiar)) {

								// Limpar os campos após a inserção do novo produto na tabela
								atualizar();

								JOptionPane.showMessageDialog(rootPane,
										entrada + " Quilos do Produto " + produto + " foram Beneficiados com sucesso.");
								atualizar();
							}
						} catch (HeadlessException | SQLException e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(rootPane, "Erro Drástico!");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Por Favor, selecine um Produto antes de Finalzar o processo de Beneficiamento!");
				}
			}
		});

		JLabel lblDatahora = new JLabel("Data");

		JLabel lblProduto = new JLabel("Produto");

		textDescricao = new JTextField();
		textDescricao.setEditable(false);
		textDescricao.setColumns(10);

		JLabel lblLote = new JLabel("Lote");

		textLote = new JTextField();
		textLote.setEditable(false);
		textLote.setColumns(10);

		textRendimento = new JTextField();
		textRendimento.setColumns(10);

		JButton btnCalcular = new JButton("=");
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					float entrada;
					float saida;
					float entrada2;
					float saida2;
					float rendimento;

					entrada = Float.parseFloat(textEntrada.getText());
					entrada2 = Float.parseFloat(textEntrada.getText());
					saida = Float.parseFloat(textSaida.getText());
					saida2 = Float.parseFloat(textSaida.getText());

					if (entrada2 <= 0 | saida2 <= 0) {
						JOptionPane.showMessageDialog(null, "Informe valores Válidos para Peso de Entrada e Saída!");
					} else {
						rendimento = (saida / entrada) * 100;
						textRendimento.setText(String.valueOf(rendimento));
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null,
							"Os campos Entrada, Saida e Rendimento devem conter algum valor para ser calculado!");
				}
			}
		});

		JLabel lblQualidade = new JLabel("Peso Sa\u00EDda");

		textEntrada = new JTextField();
		textEntrada.setColumns(10);

		textSaida = new JTextField();
		textSaida.setColumns(10);

		JLabel lblNewLabel = new JLabel("Peso Entrada");

		textProduto = new JTextField();
		textProduto.setEditable(false);
		textProduto.setColumns(10);

		JButton btnIniciar = new JButton("Iniciar Beneficiamento");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textIdProduto.getText().equals("")) {
					JOptionPane.showMessageDialog(rootPane, "Porfavor, preencha os campos adequadamente!");
				} else {
					if (textEntrada.getText().equals("0.0")) {
						JOptionPane.showMessageDialog(rootPane, "Porfavor, defina uma Quantidade para Beneficiar!");
					} else {

						String idProduto = textIdProduto.getText();
						Date data = (Date) dataBeneficiar.getDate();
						String produto = textProduto.getText();
						String descricao = textDescricao.getText();
						String lote = textLote.getText();
						String talhao = textVariedade.getText();
						float entrada = Float.parseFloat(textEntrada.getText());
						float saida = Float.parseFloat(textSaida.getText());
						float quebra = Float.parseFloat(textRendimento.getText());

						try {
							if (control.insert(idProduto, data, produto, descricao, lote, talhao, entrada, saida,
									quebra)) {
								JOptionPane.showMessageDialog(rootPane,
										entrada + " Quilos do Lote " + lote + " foram enviados para Beneficiamento.");

								// Limpar os campos e Atualizar
								atualizar();

							}
						} catch (HeadlessException | SQLException e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(rootPane, "Erro no preenchimento dos campos!");

						}
					}
				}
			}
		});

		JLabel lblQualidade_1 = new JLabel("Talh\u00E3o");

		textVariedade = new JTextField();
		textVariedade.setEditable(false);
		textVariedade.setColumns(10);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Beneficiar.this.setVisible(false);
			}
		});

		JLabel lblRendimento = new JLabel("Rendimento");

		textIdProduto = new JTextField();
		textIdProduto.setEnabled(false);
		textIdProduto.setEditable(false);
		textIdProduto.setColumns(10);

		JLabel lblId = new JLabel("Key");

		JButton btnCancelarBeneficiamento = new JButton("Remover Produto");
		btnCancelarBeneficiamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRow() != -1) {
					int op = JOptionPane.showConfirmDialog(null,
							"Deseja Realmente Cancelar o Beneficiamento do Produto selecionado?", "Excluir Item",
							JOptionPane.YES_NO_OPTION);
					if (op != 2 && op != -1) {
						if (op == 0) {

							try {
								bDAO.delete(selected);

								DefaultTableModel modelo = (DefaultTableModel) table.getModel();
								modelo.removeRow(selectedRow);
								JOptionPane.showMessageDialog(null, "Beneficiamento cancelado.");

								// Limpar os campos
								atualizar();

							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				} else {
					JOptionPane.showMessageDialog(rootPane,
							"Por favor selecione um Produto para remover do processo de Beneficiamento");
				}
			}
		});

		JLabel label = new JLabel("%");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JButton btnNewButton = new JButton("Atualizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					bDAO.deleteZero();
					atualizar();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JLabel lblId_1 = new JLabel("ID");

		textId = new JTextField();
		textId.setEditable(false);
		textId.setEnabled(false);
		textId.setColumns(10);

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblId_1, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDatahora)
								.addComponent(lblQualidade_1)
								.addComponent(lblLote)
								.addComponent(lblProduto)
								.addComponent(lblNewLabel)
								.addComponent(lblQualidade))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(textSaida, Alignment.TRAILING)
										.addComponent(textEntrada, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
										.addComponent(textVariedade)
										.addComponent(textLote)
										.addComponent(textProduto))
									.addGap(14)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(textDescricao, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(btnCalcular)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel.createSequentialGroup()
													.addComponent(textRendimento, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(label))
												.addComponent(lblRendimento))))
									.addGap(41))
								.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
									.addComponent(textId, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblId)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textIdProduto, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
								.addComponent(dataBeneficiar, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnNewButton)
								.addComponent(btnSair)))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnIniciar, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEncerrar)
							.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
							.addComponent(btnCancelarBeneficiamento)))
					.addGap(105))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnIniciar)
						.addComponent(btnEncerrar)
						.addComponent(btnCancelarBeneficiamento))
					.addGap(7)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblId_1)
						.addComponent(btnNewButton)
						.addComponent(textId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblId)
						.addComponent(textIdProduto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblDatahora)
						.addComponent(dataBeneficiar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textProduto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProduto)
						.addComponent(textDescricao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLote)
						.addComponent(textLote, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblQualidade_1)
								.addComponent(textVariedade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(20)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(textEntrada, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel))
							.addGap(17))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblRendimento)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblQualidade)
						.addComponent(textSaida, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCalcular, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(textRendimento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label)
						.addComponent(btnSair))
					.addGap(53))
		);
		panel.setLayout(gl_panel);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (table.getSelectedRow() != -1) {
					textId.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
					textIdProduto.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					dataBeneficiar.setDate((Date) table.getValueAt(table.getSelectedRow(), 2));
					textProduto.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
					textDescricao.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
					textLote.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
					textVariedade.setText(table.getValueAt(table.getSelectedRow(), 6).toString());
					textEntrada.setText(table.getValueAt(table.getSelectedRow(), 7).toString());
					textSaida.setText(table.getValueAt(table.getSelectedRow(), 8).toString());
					textRendimento.setText(table.getValueAt(table.getSelectedRow(), 9).toString());

				}
			}
		});
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null, null, null, null, null }, },
				new String[] { "Id", "Key", "Data", "Produto", "Descri\u00E7\u00E3o", "Lote", "Talhao", "Entrada",
						"Sa\u00EDda", "Rendimento" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(64);
		table.getColumnModel().getColumn(3).setPreferredWidth(64);
		table.getColumnModel().getColumn(5).setPreferredWidth(49);
		table.getColumnModel().getColumn(7).setPreferredWidth(105);
		table.getColumnModel().getColumn(7).setMinWidth(25);
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);

		// Método que Preenche a tabela de produtos em processo de Beneficiamento
		preencheTable();
	}

	public void puxarValores(BeneficiarModel model) {
		textIdProduto.setText(model.getIdProduto());
		textProduto.setText(model.getProduto());
		textDescricao.setText(model.getDescricao());
		textLote.setText(model.getLote());
		textVariedade.setText(model.getTalhao());
		textEntrada.setText("0.0");
		textSaida.setText("0.0");
		textRendimento.setText("0.0");

	}

	public void preencheTable() {
		try {
			List<BeneficiarModel> lista = bDAO.consultaBeneficiamento();
			DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			table.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));

			if (modelo.getRowCount() > 0) {
				modelo.setRowCount(0);
			}
			for (BeneficiarModel p : lista) {
				Object[] objeto = new Object[10];
				objeto[0] = p.getIdBeneficiar();
				objeto[1] = p.getIdProduto();
				objeto[2] = p.getData();
				objeto[3] = p.getProduto();
				objeto[4] = p.getDescricao();
				objeto[5] = p.getLote();
				objeto[6] = p.getTalhao();
				objeto[7] = p.getEntrada();
				objeto[8] = p.getSaida();
				objeto[9] = p.getRendimento();

				modelo.addRow(objeto);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao carregar a Tabela");
		}

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {

				ListSelectionModel lsm = (ListSelectionModel) event.getSource();
				if (!lsm.isSelectionEmpty()) {
					selectedRow = lsm.getMinSelectionIndex();
					selected = (int) table.getValueAt(selectedRow, 0);
				}
			}
		});
	}

	public void atualizar() {
		preencheTable();

		textIdProduto.setText("");
		textProduto.setText("");
		textDescricao.setText("");
		textLote.setText("");
		textVariedade.setText("");
		textEntrada.setText("");
		textSaida.setText("");
		textRendimento.setText("");

	}
}