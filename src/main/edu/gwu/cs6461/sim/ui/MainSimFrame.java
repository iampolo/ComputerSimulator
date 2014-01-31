package edu.gwu.cs6461.sim.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javaapplication1.Register;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.apache.log4j.Logger;

import edu.gwu.cs6461.sim.common.RegisterName;
import edu.gwu.cs6461.sim.util.GriddedPanel;
import edu.gwu.cs6461.sim.util.TextAreaAppender;

/**
 * 
 * @author marcoyeung
 * 
 */
public class MainSimFrame extends JFrame {

	private final static Logger logger = Logger.getLogger(MainSimFrame.class);
	private final static Logger simConsole = Logger.getLogger("simulator.console");

	private JCheckBox[] chkBinData  = new JCheckBox[20]; 
	private JRadioButton[] radBinData  = new JRadioButton[20];
	private JLabel[] lblBinPosInfo = new JLabel[20];
	private Dimension shortField = new Dimension( 100, 50 );
	
	
	private JLabel lblR0 = new JLabel("R0");
	private JLabel lblR1 = new JLabel("R1");
	private JLabel lblR2 = new JLabel("R2");
	private JLabel lblR3 = new JLabel("R3");
	
	private JTextField txtR0 = new JTextField(10);
	private JTextField txtR1 = new JTextField(10);
	private JTextField txtR2 = new JTextField(10);
	private JTextField txtR3 = new JTextField(10);
	
	private JLabel lblX1 = new JLabel("X1");
	private JLabel lblX2 = new JLabel("X2");
	private JLabel lblX3 = new JLabel("X3");

	private JTextField txtX1 = new JTextField(15);
	private JTextField txtX2 = new JTextField(15);
	private JTextField txtX3 = new JTextField(15);

	private JLabel lblMAR = new JLabel("MAR");
	private JLabel lblMBR = new JLabel("MBR");
	private JLabel lblMSR = new JLabel("MSR");
	private JLabel lblMFR = new JLabel("MFR");
	
	private JTextField txtMAR = new JTextField(13);
	private JTextField txtMBR = new JTextField(20);
	private JTextField txtMSR = new JTextField(20);
	private JTextField txtMFR = new JTextField(20);
	
	private JLabel lblCC = new JLabel("CC");  
	private JLabel lblIR = new JLabel("IR");
	private JLabel lblPC = new JLabel("PC");

	private JTextField txtCC = new JTextField(4);    //condition code //UNDERFLOW or 
	private JTextField txtIR = new JTextField(20);  //current instruction 
	private JTextField txtPC = new JTextField(13);  //address of next instruction
	
	private JButton btnHalt = new JButton("Halt");
	private JButton btnRun = new JButton("Run");
	private JButton btnSingleInstr = new JButton("Single Instr.");
	private JButton btnSingleStep = new JButton("Single Step");
	
	
	private JComboBox<String> cboSwithOptions = new JComboBox<String>();
	private JButton btnReset = new JButton("Reset");
	private JButton btnLoad = new JButton("Load");
	
	private JTextArea txtConsoleText = new JTextArea();
	
	
	public MainSimFrame() {
		// setLayout(new MigLayout());

		RegisterName[] names = RegisterName.values();
		String[] reg = new String[names.length];

		for (int i = 0; i < reg.length; i++) {
			reg[i] = new String();
			reg[i] = names[i].getVal();
		}
		
		cboSwithOptions = new JComboBox<String>(reg);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				TextAreaAppender.setTextArea(txtConsoleText);
			}
		});
		
	}
	
	public void init(){

		// add(new JLabel("Test")) ;
		// add(new JButton("Start"));
		
		GriddedPanel pRegister = new GriddedPanel();
//		pRegister.setLayout(new BoxLayout(pRegister, BoxLayout.Y_AXIS));
//		pRegister.add(createSwitchPanel(0,19));
//		pRegister.add(createGeneralRPanel());
//		pRegister.add(createIndexRPanel());
//		pRegister.add(createMiscRPanel());
//		add(pRegister);
		
		
		JPanel regSwPanel = new JPanel();
		JPanel regPanel = new JPanel();
		regPanel.setLayout(new BoxLayout(regPanel, BoxLayout.X_AXIS));
//		regPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		regPanel.add(createGeneralRPanel());
		regPanel.add(createIndexRPanel());
		regPanel.add(createMiscRPanel());
		
		regSwPanel.setLayout(new GridBagLayout());
		GridBagConstraints c =new GridBagConstraints();
		
		c.gridx=0;
		c.gridy=0;
		c.gridwidth = 3;
		regSwPanel.add(regPanel,c);
		c.gridx=0;
		c.gridy=1;
		c.gridwidth = 2;
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.anchor = GridBagConstraints.FIRST_LINE_END;
		regSwPanel.add(createSwitchPanel(0, 19),c);
		c.gridx=2;
		c.gridy=1;
		c.gridwidth = 1;
		c.weightx = 0;
		c.weighty = 1;
		c.fill = GridBagConstraints.VERTICAL;
		c.anchor = GridBagConstraints.FIRST_LINE_END;
//		c.fill = GridBagConstraints.BOTH;
		regSwPanel.add(createControlPanel(), c);
		
//		c.gridx=0;
//		c.gridy=2;
//		c.gridwidth = 3;
//		regSwPanel.add(createConsolePanel(), c);
		
//		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(regSwPanel, BorderLayout.NORTH);
		add(createConsolePanel(), BorderLayout.CENTER);
		

		logger.debug(getLayout());
	}

	
	private JPanel createMiscRPanel(){
		GriddedPanel gPanel = new GriddedPanel();
		gPanel.setBorder(new TitledBorder(new EtchedBorder(), "Registers"));
		gPanel.addComponent(lblMAR,0,0);
		gPanel.addComponent(txtMAR,0,1);
		gPanel.addComponent(lblMBR,1,0);
		gPanel.addComponent(txtMBR,1,1);
		gPanel.addComponent(lblMSR,2,0);
		gPanel.addComponent(txtMSR,2,1);
		gPanel.addComponent(lblMFR,3,0);
		gPanel.addComponent(txtMFR,3,1);
		gPanel.addComponent(lblCC,4,0);
		gPanel.addComponent(txtCC,4,1);
		gPanel.addComponent(lblIR,5,0);
		gPanel.addComponent(txtIR,5,1);
		gPanel.addComponent(lblPC,6,0);
		gPanel.addComponent(txtPC,6,1);
		
//		gPanel.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.blue)));
		
		FlowLayout fl = new FlowLayout();
		JPanel wrap = new JPanel();
		wrap.setLayout(fl);
		wrap.add(gPanel);
		fl.setAlignment(FlowLayout.LEFT);
		return wrap;
	}
	private JPanel createIndexRPanel(){
		GriddedPanel gPanel = new GriddedPanel();
		gPanel.setBorder(new TitledBorder(new EtchedBorder(), "Index"));
		gPanel.addComponent(lblX1,0,0);
		gPanel.addComponent(txtX1,0,1);
		gPanel.addComponent(lblX2,1,0);
		gPanel.addComponent(txtX2,1,1);
		gPanel.addComponent(lblX3,2,0);
		gPanel.addComponent(txtX3,2,1);
//		gPanel.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.blue)));
		
		FlowLayout fl = new FlowLayout();
		JPanel wrap = new JPanel();
		wrap.setLayout(fl);
		wrap.add(gPanel);
		fl.setAlignment(FlowLayout.LEFT);
		return wrap;
	}

	private JPanel createControlPanel(){
		GriddedPanel gPanel = new GriddedPanel();
		gPanel.setBorder(new TitledBorder(new EtchedBorder(), 
			      "Control Panel"));
		
		gPanel.addComponent(btnSingleStep,0,0);
		
		
		return gPanel;
	}
	
	private JPanel createConsolePanel(){
		
		txtConsoleText.setFont( new Font("consolas", Font.PLAIN, 13));
		txtConsoleText.setEditable(false);
		JScrollPane scroll = new JScrollPane( txtConsoleText );
//		scroll.setPreferredSize(new Dimension( 240, 180 ) );
		
		JPanel gPanel = new JPanel();
		gPanel.setBorder(new TitledBorder(new EtchedBorder(), "Console"));
		gPanel.setLayout(new BorderLayout());
		gPanel.add(scroll, BorderLayout.CENTER);
		
		return gPanel;
		
	}
	
	
	private JPanel createGeneralRPanel(){
	    GriddedPanel gPanel = new GriddedPanel();
	    gPanel.setBorder(new TitledBorder(new EtchedBorder(), "General"));
	    
		gPanel.addComponent(lblR0,0,0);
		gPanel.addComponent(txtR0,0,1);
		gPanel.addComponent(lblR1,1,0);
		gPanel.addComponent(txtR1,1,1);
		gPanel.addComponent(lblR2,2,0);
		gPanel.addComponent(txtR2,2,1);
		gPanel.addComponent(lblR3,3,0);
		gPanel.addComponent(txtR3,3,1);
//		gPanel.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.blue)));
		
		FlowLayout fl = new FlowLayout();
		JPanel wrap = new JPanel();
		wrap.setLayout(fl);
		wrap.add(gPanel);
		fl.setAlignment(FlowLayout.LEFT);
		return wrap;
		
	}

	
	private JPanel createBinPanel(int start, int end) {
		JPanel pBinPanel = new JPanel();
		
//		pBinPanel.setLayout(new GridBagLayout());
		pBinPanel.setLayout(new BoxLayout(pBinPanel,BoxLayout.X_AXIS));
		
		JPanel tmp = null;
		for (int i = start; i <= end; i++) {
			tmp = new JPanel();
			tmp.setLayout(new GridLayout(2,1,0,0));
			
			lblBinPosInfo[i] = new JLabel(String.valueOf(i));
			lblBinPosInfo[i].setHorizontalAlignment(SwingConstants.CENTER);
			lblBinPosInfo[i].setAlignmentY(RIGHT_ALIGNMENT);
//			lblBinPosInfo[i].setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.GREEN)));
			tmp.add(lblBinPosInfo[i]);
			
			radBinData[i] = new JRadioButton();
//			radBinData[i].setBorder(BorderFactory.createEmptyBorder());
			logger.debug("bindata created:" + i);
			tmp.add(radBinData[i]);
//			tmp.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.blue)));
			pBinPanel.add(tmp);
		}
		tmp.setBorder(BorderFactory.createEmptyBorder());
		
//		pBinPanel.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.blue)));
		
		return pBinPanel; 
	}

	private JPanel createSwitchPanel(int start, int end){
		
		
		//Button only
		JPanel btnPanel = new JPanel();
		
		btnPanel.add(cboSwithOptions);
		cboSwithOptions.addActionListener(new SwitchComboActionListener());
		cboSwithOptions.setSelectedIndex(0);
		btnPanel.add(btnReset);
		btnPanel.add(btnLoad);
		FlowLayout fl = new FlowLayout();
		fl.setAlignment(FlowLayout.RIGHT);
		btnPanel.setLayout(fl);
		//Button only 		
		
		JPanel wrapPanel = new JPanel();
		wrapPanel.setBorder(new TitledBorder(new EtchedBorder(), "Switches"));
//		wrapPanel.setLayout(new GridLayout(2,1,0,0));
		wrapPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints c= new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		wrapPanel.add(createBinPanel(start, end),c);
		c.gridy = 1;
		wrapPanel.add(btnPanel,c);
		
		return wrapPanel;
	}
	

	

	private JPanel createGeneralRPanelGrid(){
		
		
		JPanel pGeneral = new JPanel();
	    pGeneral.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		JPanel pGLeft = new JPanel(new GridLayout(4,1,3,3));
		JPanel pGRight = new JPanel(new GridLayout(4,1,3,3));
		
		pGLeft.add(lblR0);
		pGRight.add(txtR0);
		pGLeft.add(lblR1);
		pGRight.add(txtR1);
		pGLeft.add(lblR2);
		pGRight.add(txtR2);
		pGLeft.add(lblR3);
		pGRight.add(txtR3);
		
		pGeneral.setLayout(new BorderLayout());
		pGeneral.add(pGLeft, BorderLayout.WEST);
		pGeneral.add(pGRight, BorderLayout.CENTER); //resizable
		
		return pGeneral;
		
	}
	
	private JPanel createMiscRPanelGrid(){
		JPanel pMisc = new JPanel();
	    pMisc.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		JPanel pGLeft = new JPanel(new GridLayout(7,1,3,3));
		JPanel pGRight = new JPanel(new GridLayout(7,1,3,3));

		pGLeft.add(lblMAR);
		pGRight.add(txtMAR);
		pGLeft.add(lblMBR);
		pGRight.add(txtMBR);
		pGLeft.add(lblMSR);
		pGRight.add(txtMSR);
		pGLeft.add(lblMFR);
		pGRight.add(txtMFR);
		pGLeft.add(lblCC);
		pGRight.add(txtCC);
		pGLeft.add(lblIR);
		pGRight.add(txtIR);
		pGLeft.add(lblPC);
		pGRight.add(txtPC);

		pMisc.setLayout(new BorderLayout());
		pMisc.add(pGLeft, BorderLayout.WEST);
		pMisc.add(pGRight, BorderLayout.CENTER); //resizable
		
		return pMisc;
		
	}
	
	/**
	 * @deprecated
	 * @param start
	 * @param end
	 * @return
	 */
	private JPanel createRadioPanel(int start, int end) {
		
		JPanel sInter = new JPanel();
		sInter.setLayout(new GridBagLayout());
		
		JPanel lPanel = new JPanel();
		lPanel.setLayout(new GridLayout(1,(end-start),1,1));		
		
		JPanel sPanel = new JPanel();
		sPanel.setLayout(new GridLayout(1,(end-start),2,1));
		
		for (int i = start; i <= end; i++) {
			
			lblBinPosInfo[i] = new JLabel(String.valueOf(i));
			lblBinPosInfo[i].setPreferredSize(shortField);
			lPanel.add(lblBinPosInfo[i]);
			
			radBinData[i] = new JRadioButton();
//			radBinData[i].setBorder(BorderFactory.createEmptyBorder());
			logger.debug("bindata created:" + i);
			sPanel.add(radBinData[i]);
		}

		GridBagConstraints c = new GridBagConstraints();
		c.gridy = 0;
		sInter.add(lPanel,c);
		c.gridy = 1;
		sInter.add(sPanel,c);
		sPanel.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.blue)));
		return sInter;
	}
	
	private JPanel createIndexRPanelGrid(){
		
		JPanel pIndex = new JPanel();
	    pIndex.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		JPanel pGLeft = new JPanel(new GridLayout(3,1,3,3));
		JPanel pGRight = new JPanel(new GridLayout(3,1,3,3));

		pGLeft.add(lblX1);
		pGRight.add(txtX1);
		pGLeft.add(lblX2);
		pGRight.add(txtX2);
		pGLeft.add(lblX3);
		pGRight.add(txtX3);

		pIndex.setLayout(new BorderLayout());
		pIndex.add(pGLeft, BorderLayout.WEST);
		pIndex.add(pGRight, BorderLayout.CENTER); //resizable
		
		return pIndex;
		
	}

	private class SwitchComboActionListener implements ActionListener{

		private void maskSwitches(int start, int end,boolean b){
			
			for (int i = start; i <= end; i++) {
				if (radBinData[i]!=null) {
					radBinData[i].setEnabled(b);
				}
			}
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String selected = (String)((JComboBox)e.getSource()).getSelectedItem();
			if (RegisterName.X1.getVal().equals(selected)) {
				maskSwitches(0, 1, true);
				maskSwitches(2, 19, false);
			} else 
				maskSwitches(0, 19, true);
			
			logger.debug("selcted :  " + selected);
			
		}
		
	}
	
}

