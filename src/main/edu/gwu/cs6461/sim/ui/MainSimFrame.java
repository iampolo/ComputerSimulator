package edu.gwu.cs6461.sim.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
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

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;

import org.apache.log4j.Logger;

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
	
	private JLabel lblX1 = new JLabel("R1");
	private JLabel lblX2 = new JLabel("R2");
	private JLabel lblX3 = new JLabel("R3");

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
	
	
	private JComboBox swithOptions = new JComboBox();
	private JButton btnReset = new JButton("Reset");
	private JButton btnLoad = new JButton("Load");
	
	private JTextArea txtConsoleText = new JTextArea();
	
	
	public MainSimFrame() {
		// setLayout(new MigLayout());

		
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
		
		pRegister.addSpannedComponent(createSwitchPanel(10, 19),0,0,2,1);
		pRegister.addComponent(createGeneralRPanel(),1,0,2,1,GridBagConstraints.EAST,GridBagConstraints.HORIZONTAL);
		pRegister.addComponent(createIndexRPanel(),2,0,2,1,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL);
		pRegister.addComponent(createMiscRPanel(),3,0,2,1,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL);
		pRegister.addComponent(createConsolePanel(),5,0,2,2,GridBagConstraints.CENTER,GridBagConstraints.BOTH);
		pRegister.addComponent(createControlPanel(),4,0,2,1,GridBagConstraints.EAST,GridBagConstraints.NONE);
		
//		simConsole.debug("Starting Simulator.");
		
		add(pRegister);
		
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
	
	private JPanel createMiscRPanel(){
		GriddedPanel gPanel = new GriddedPanel();
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
		
		gPanel.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.blue)));
		
		FlowLayout fl = new FlowLayout();
		JPanel wrap = new JPanel();
		wrap.setLayout(fl);
		wrap.add(gPanel);
		fl.setAlignment(FlowLayout.LEFT);
		return wrap;
	}
	private JPanel createIndexRPanel(){
		GriddedPanel gPanel = new GriddedPanel();
		gPanel.addComponent(lblX1,0,0);
		gPanel.addComponent(txtX1,0,1);
		gPanel.addComponent(lblX2,1,0);
		gPanel.addComponent(txtX2,1,1);
		gPanel.addComponent(lblX3,2,0);
		gPanel.addComponent(txtX3,2,1);
		gPanel.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.blue)));
		
		FlowLayout fl = new FlowLayout();
		JPanel wrap = new JPanel();
		wrap.setLayout(fl);
		wrap.add(gPanel);
		fl.setAlignment(FlowLayout.LEFT);
		return wrap;
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
		scroll.setPreferredSize(new Dimension( 240, 180 ) );
		
		JPanel gPanel = new JPanel();
		gPanel.setBorder(new TitledBorder(new EtchedBorder(), "Console"));
		gPanel.setLayout(new BorderLayout());
		gPanel.add(scroll, BorderLayout.CENTER);
		
		return gPanel;
		
	}
	
	
	private JPanel createGeneralRPanel(){
	    GriddedPanel gPanel = new GriddedPanel();
	    
		gPanel.addComponent(lblR0,0,0);
		gPanel.addComponent(txtR0,0,1);
		gPanel.addComponent(lblR1,1,0);
		gPanel.addComponent(txtR1,1,1);
		gPanel.addComponent(lblR2,2,0);
		gPanel.addComponent(txtR2,2,1);
		gPanel.addComponent(lblR3,3,0);
		gPanel.addComponent(txtR3,3,1);
		gPanel.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.blue)));
		
		FlowLayout fl = new FlowLayout();
		JPanel wrap = new JPanel();
		wrap.setLayout(fl);
		wrap.add(gPanel);
		fl.setAlignment(FlowLayout.LEFT);
		return wrap;
		
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
	
	
	private JPanel createBinPanel(int start, int end) {
		JPanel pBinPanel = new JPanel();
		pBinPanel.setLayout(new GridBagLayout());
		
		JPanel tmp = null;
		for (int i = start; i <= end; i++) {
			tmp = new JPanel();
			tmp.setLayout(new GridLayout(2,1,0,0));
			
			lblBinPosInfo[i] = new JLabel(String.valueOf(i));
			lblBinPosInfo[i].setPreferredSize(shortField);
			lblBinPosInfo[i].setHorizontalAlignment(SwingConstants.CENTER);
//			lblBinPosInfo[i].setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.GREEN)));
			tmp.add(lblBinPosInfo[i]);
			
			radBinData[i] = new JRadioButton();
//			radBinData[i].setBorder(BorderFactory.createEmptyBorder());
			logger.debug("bindata created:" + i);
			tmp.add(radBinData[i]);
			tmp.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.blue)));
			pBinPanel.add(tmp);
		}
		tmp.setBorder(BorderFactory.createEmptyBorder());
		
		pBinPanel.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.blue)));
		
		return pBinPanel; 
	}
	private JPanel createSwitchPanel(int start, int end){
		
		
		
		JPanel btnPanel = new JPanel();
	    swithOptions.addItem("R0");
	    swithOptions.addItem("X0");
	    swithOptions.addItem("IR");
	    swithOptions.addItem("PC");
	    swithOptions.addItem("MAR");
	    swithOptions.addItem("Immed");
		
		btnPanel.add(swithOptions);
		btnPanel.add(btnReset);
		btnPanel.add(btnLoad);
		FlowLayout fl = new FlowLayout();
		fl.setAlignment(FlowLayout.RIGHT);
		btnPanel.setLayout(fl);
		
		JPanel wrapPanel = new JPanel();
		wrapPanel.setLayout(new GridLayout(2,1,0,0));
		wrapPanel.add(createBinPanel(start, end));
		wrapPanel.add(btnPanel);
		
		return wrapPanel;
	}
	

	/**
	 * 	 @deprecated
	 * @param start
	 * @param end
	 * @return
	 */
	private JPanel createChkPanel(int start, int end){
		
		JPanel sInter = new JPanel();
		sInter.setLayout(new GridBagLayout());
		JPanel sPanel = new JPanel();
		sPanel.setLayout(new GridLayout(1,(end-start+1),1,1));
		
		for (int i = start; i <= end; i++) {
			chkBinData[i] = new JCheckBox();
			chkBinData[i].setBorder(BorderFactory.createEmptyBorder());
			logger.debug("bindata created:" + i);
			sPanel.add(chkBinData[i]);
		}
		sInter.add(sPanel);
		sPanel.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.blue)));
		return sInter;
		
	}
	
	private JPanel createPanel() {

		// JPanel panel = new JPanel(new MigLayout());
		
		
		MigLayout layout = new MigLayout("fillx", "[right]rel[left,grow]", "[]1[]"); ///[grow,fill]
		JPanel panel = new JPanel(layout);

		panel.add(new JLabel("PC:"));
		panel.add(new JTextField(""), new CC().wrap());
		JLabel lblIR = new JLabel("IR:");
		lblIR.setToolTipText("============");
		panel.add(lblIR);
		panel.add(new JTextField(""));
		

		// panel.add(new JScrollPane(new JTextArea(200,100)), "wrap,grow");

		panel.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
		return panel;
	}


}

