package controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.View;

import model.Airport;
import model.Obstacle;
import model.Runway;
import view.MainPageGUI;
import view.SelectImportRunwaysGUI;
import view.SideViewGUI;
import view.TopViewGUI;
import view.ViewGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by steff on 01/03/2017.
 */
public class Controller {

	private ReadObstacleXMLFile readnwrite = new ReadObstacleXMLFile();
	private ReadAirportXMLFile airportXML = new ReadAirportXMLFile();

	private SelectImportRunwaysGUI sir;
	private Airport airport;
	private MainPageGUI gui;
	private ActionListener importAirport;
	private ActionListener exportAirport;
	private ActionListener submitButtonPress;
	private ActionListener refreshButtonPress;
	private ActionListener addObstacleButtonPress;
	private ActionListener chooseCurrentRunway;
	private ActionListener refreshMainButtonPress;
	private ActionListener changeRunwayUse;
	private ActionListener importSomeRunways;
	private ActionListener overwriteAirport;
	private ActionListener selectAllItems;
	private ActionListener zoomIn;
	private ActionListener rotate;
	private ActionListener rotateToHeading;
	private ActionListener reset;
	private MouseListener getTopViewPoint;
	private File obstacleList;

	public MouseListener getGetTopViewPoint() {
		return getTopViewPoint;
	}

	public ActionListener getReset() {
		return reset;
	}

	public ActionListener getRotateToHeading() {
		return rotateToHeading;
	}

	public ActionListener getZoomIn() {
		return zoomIn;
	}

	public ActionListener getRotate() {
		return rotate;
	}

	public ArrayList<Obstacle> listOfObstacles = new ArrayList<Obstacle>();

	public ActionListener getImportAirport() {
		return importAirport;
	}

	public ActionListener getSelectAllItems() {
		return selectAllItems;
	}

	public ActionListener getImportSomeRunways() {
		return importSomeRunways;
	}

	public ActionListener getOverwriteAirport() {
		return overwriteAirport;
	}

	public ActionListener getExportAirport() {
		return exportAirport;
	}

	public ActionListener getRefreshMainButtonPress() {
		return refreshMainButtonPress;
	}

	public ActionListener getSubmitButtonPress() {
		return submitButtonPress;
	}

	public ActionListener getRefreshButtonPress() {
		return refreshButtonPress;
	}

	public ActionListener getChooseCurrentRunway() {
		return chooseCurrentRunway;
	}

	public ActionListener getAddObstacleButtonPress() {
		return addObstacleButtonPress;
	}

	public MainPageGUI getGui() {
		return gui;
	}

	public ArrayList<Obstacle> getList() {
		System.out.println(listOfObstacles);
		return listOfObstacles;
	}

	public ActionListener getChangeRunwayUse() {
		return changeRunwayUse;
	}

	public boolean checkFileExists() {
		if (!obstacleList.exists()) {
			try {
				obstacleList.createNewFile();
				return false;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public void outputFromList() throws ClassNotFoundException {
		try {
			listOfObstacles = readnwrite.read();
			System.out.println(listOfObstacles.size());
			for (Obstacle e : listOfObstacles) {
				System.out.println(e.getName());

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void putObstacle() {
		try {
			outputFromList();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		gui.getObstacleBox().removeAllItems();
		gui.getViewObstaclesList().removeAllItems();
		for (Obstacle o : listOfObstacles) {
			gui.getObstacleBox().addItem(o.getName());
			gui.getViewObstaclesList().addItem(o.getName());
		}
	}

	public void writeObstacleList() {
		try {
			String name = gui.getObstacleName();
			int height = gui.getObstacleHeight();
			int width = gui.getObstacleWidth();
			int length = gui.getObstacleLength();
			Obstacle o = new Obstacle(name, height, width, length);
			listOfObstacles.add(o);
			System.out.println(listOfObstacles);
			readnwrite.write(listOfObstacles);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Controller() {
		// create an action listener for when the export button is pressed.
		exportAirport = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Would you like to export the current aiport details?",
						"Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					airportXML.write(airport);
					JOptionPane.showMessageDialog(gui, "Aiport Exported.");
				} else {
					JOptionPane.showMessageDialog(gui, "Aiport not exported.");
				}
			}
		};

		getTopViewPoint = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				JTextField x = gui.getPointx();
				JTextField y = gui.getPointy();

				x.setText(String.valueOf(arg0.getX()));
				y.setText(String.valueOf(arg0.getY()));

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		};

		zoomIn = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (gui.getZoomFactor().getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Zoom Factor is empty. Check again.", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						TopViewGUI a = gui.getTopView();
						a.setZoom(Double.parseDouble(gui.getZoomFactor().getText()));
						a.setPoint((Double.parseDouble(gui.getPointx().getText())),
								(Double.parseDouble(gui.getPointy().getText())));
						a.repaint();
						JOptionPane.showMessageDialog(null, "Zoom Completed.");
					}
				} catch (NumberFormatException nfe) {
					nfe.printStackTrace();
				}
			}

		};
		// has to do it twice dunno why
		reset = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gui.updateGraphicRunway();
				gui.updateGraphicRunway();
				JOptionPane.showMessageDialog(null, "View Reset.");
			}
		};

		rotate = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (gui.getRotationDegree().getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Rotation degree is empty. Check again.", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						TopViewGUI a = gui.getTopView();
						a.setOrientation(Math.toRadians(Double.parseDouble(gui.getRotationDegree().getText())));
						a.repaint();
						JOptionPane.showMessageDialog(null, "View Rotated.");
					}
				} catch (NumberFormatException nfe) {
					nfe.printStackTrace();
				}
			}
		};
		rotateToHeading = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TopViewGUI a = gui.getTopView();
				a.setOrientation(Math.toRadians(airport.getCurrentRunway().getOrientation() * 10));
				a.repaint();
				JOptionPane.showMessageDialog(null, "View rotated to match compass heading.");
			}

		};

		selectAllItems = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JList<Runway> list = sir.getList();
				list.setSelectionInterval(0, list.getModel().getSize());
				JOptionPane.showMessageDialog(null, "Selecting all runways to be imported.");
			}
		};

		importSomeRunways = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JList<Runway> list = sir.getList();
				for (int i = 0; i < list.getModel().getSize(); i++) {
					if (list.isSelectedIndex(i)) {
						airport.addRunway(list.getModel().getElementAt(i));
					}
				}
				// needs to remove choose current runway or it will output a
				// null pointer exception
				// this is because a time the list will be empty
				if (JOptionPane.showConfirmDialog(null, "Would you like to import this runway?", "Confirm",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					gui.getRunways().removeActionListener(chooseCurrentRunway);
					gui.updateRunwayList(airport.getListOfRunways());
					gui.getRunways().addActionListener(chooseCurrentRunway);
					gui.updateGraphicRunway();
					Runway currentRunway = airport.getCurrentRunway();
					// add the figures to the gui
					gui.setOriginalFigures(currentRunway.getTodaOriginal(), currentRunway.getToraOriginal(),
							currentRunway.getLdaOriginal(), currentRunway.getAsdaOriginal());
					gui.setAdjustedFigures(currentRunway.getToda(), currentRunway.getTora(), currentRunway.getLda(),
							currentRunway.getAsda());

					sir.setVisible(false);
					JOptionPane.showMessageDialog(gui, "Runway added. List updated.");
				} else {
					JOptionPane.showMessageDialog(gui, "Runway not added.");
				}
			}
		};

		overwriteAirport = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JList<Runway> list = sir.getList();
				airport = new Airport(new ArrayList<Runway>());
				for (int i = 0; i < list.getModel().getSize(); i++) {
					if (list.isSelectedIndex(i)) {
						airport.addRunway(list.getModel().getElementAt(i));
					}
				}

				if (JOptionPane.showConfirmDialog(null,
						"Would you like to import this runway and overwrite the current?", "Confirm",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					// needs to remove choose current runway or it will output a
					// null pointer exception
					// this is because a time the list will be empty
					gui.getRunways().removeActionListener(chooseCurrentRunway);
					gui.updateRunwayList(airport.getListOfRunways());
					gui.getRunways().addActionListener(chooseCurrentRunway);
					gui.updateGraphicRunway();
					Runway currentRunway = airport.getCurrentRunway();
					// add the figures to the gui
					gui.setOriginalFigures(currentRunway.getTodaOriginal(), currentRunway.getToraOriginal(),
							currentRunway.getLdaOriginal(), currentRunway.getAsdaOriginal());
					gui.setAdjustedFigures(currentRunway.getToda(), currentRunway.getTora(), currentRunway.getLda(),
							currentRunway.getAsda());

					sir.setVisible(false);
					JOptionPane.showMessageDialog(gui, "Runway added. Old runway is overwritten.");
				} else {
					JOptionPane.showMessageDialog(gui, "Runway not added. Data isn't overwritten.");
				}
			}
		};

		importAirport = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Runway> readRunways = airportXML.read().getListOfRunways();
				sir = new SelectImportRunwaysGUI(readRunways, Controller.this);

			}
		};

		// create an action listener for when the submit button is pressed.
		submitButtonPress = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					gui.addObstacleToViews();
					Runway inputRunway = airport.getCurrentRunway();
					inputRunway.addObstacle(gui.getNewObstacle());
					Runway recip = airport.findReciprocal(inputRunway);
					if (recip != null)
					{
						recip.addObstacle(gui.getRecipObstacle());
					}
					gui.setAdjustedFigures(inputRunway.getToda(), inputRunway.getTora(), inputRunway.getLda(),
							inputRunway.getAsda());
					gui.setRecipAdjustedFigures(recip.getToda(), recip.getTora(), recip.getLda(), recip.getAsda());
					JOptionPane.showMessageDialog(gui, "Obstacle added to runway.");

				} catch (NumberFormatException nfe) {
					gui.updateGraphicRunway();
					gui.updateGraphicRunway();
					nfe.printStackTrace();
				}
			}
		};

		refreshButtonPress = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gui.setTodaWorking(Runway.todaworking);
				gui.setToraWorking(Runway.toraworking);
				gui.setAsdaWorking(Runway.asdaworking);
				gui.setLdaWorking(Runway.ldaworking);
				JOptionPane.showMessageDialog(null, "Calculations updated.");
			}
		};

		addObstacleButtonPress = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (JOptionPane.showConfirmDialog(null, "Would you like to add this obstacle?", "Confirm",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						if (gui.getHeightBox().getText().isEmpty() || gui.getWidthBox().getText().isEmpty()
								|| gui.getObstacleNameBox().getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Textfield is empty. Check again.", "Error",
									JOptionPane.ERROR_MESSAGE);
						} else {
							writeObstacleList();
							putObstacle();
							JOptionPane.showMessageDialog(gui, "Obstacle Added.");
						}
					} else {
						JOptionPane.showMessageDialog(gui, "Obstacle not added to the list.");
					}
				} catch (NumberFormatException nfe) {
					nfe.printStackTrace();
				}
			}
		};

		changeRunwayUse = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox box = (JComboBox) e.getSource();
				if (box.getSelectedItem().toString().equals("Landing")) {
					if (JOptionPane.showConfirmDialog(null, "Would you like to change to Landing view?", "Confirm",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						gui.changeRunwayUse((String) box.getSelectedItem());
						JOptionPane.showMessageDialog(gui, "View Changed to Landing. See Below.");
					} else {
						JOptionPane.showMessageDialog(gui, "View Unchanged.");
					}
				} else if (box.getSelectedItem().toString().equals("Take Off")) {
					if (JOptionPane.showConfirmDialog(null, "Would you like to change to Take Off view?", "Confirm",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						gui.changeRunwayUse((String) box.getSelectedItem());
						JOptionPane.showMessageDialog(gui, "View Changed to Take Off. See Below.");
					} else {
						JOptionPane.showMessageDialog(gui, "View Unchanged.");
					}
				}
			}
		};

		refreshMainButtonPress = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				// STOPS IT FROM TRYING TO LOOK AT A EMPTY LIST
				if (gui.getObstacleNames().getItemCount() != 0) {
					for (Obstacle o : listOfObstacles) {
						if (gui.getObstacleBox().getSelectedItem().toString().equals(o.getName())) {
							gui.getHeightBox().setText(Integer.toString(o.getObstacleHeight()));
							gui.getWidthBox().setText(Integer.toString(o.getObstacleWidth()));
							gui.getLengthBox().setText(Integer.toString(o.getObstacleLength()));
						}
					}
				}
			}
		};

		// changes current runway and shows its values
		chooseCurrentRunway = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				airport.setCurrentRunway(gui.getSelectedRunway());
				gui.setOriginalFigures(airport.getCurrentRunway().getTodaOriginal(),
						airport.getCurrentRunway().getToraOriginal(), airport.getCurrentRunway().getLdaOriginal(),
						airport.getCurrentRunway().getAsdaOriginal());
				gui.setAdjustedFigures(airport.getCurrentRunway().getToda(), airport.getCurrentRunway().getTora(),
						airport.getCurrentRunway().getLda(), airport.getCurrentRunway().getAsda());

				Runway inverse = airport.findReciprocal(airport.getCurrentRunway());

				if (inverse != null) {

					gui.setRecipOriginalFigures(inverse.getTodaOriginal(), inverse.getToraOriginal(),
							inverse.getLdaOriginal(), inverse.getAsdaOriginal());
					gui.setRecipAdjustedFigures(inverse.getToda(), inverse.getTora(), inverse.getLda(),
							inverse.getAsda());

				}

				gui.updateGraphicRunway();
				gui.updateGraphicRunway();
			}
		};

		// create and init the GUI
		gui = new MainPageGUI();
		gui.init(this);
		putObstacle();

		gui.getRunwayViews().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gui.refreshViews();
			}
		});

		gui.getViewObstaclesList().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// stops null pointer execption
				if (gui.getViewObstaclesList().getItemCount() != 0) {
					gui.getDisplayObstacle().setText("");
					for (Obstacle o : listOfObstacles) {
						if (gui.getViewObstaclesList().getSelectedItem().toString().equals(o.getName())) {
							gui.getDisplayObstacle()
									.append("Name   " + o.getName() + "\t" + "Height:   " + o.getObstacleHeight() + "\t"
											+ "Width:   " + o.getObstacleWidth() + "\t" + "Length:   "
											+ o.getObstacleLength() + "\n");
						}
					}
				}
			}

		});

		// create the model with a single runway
		ArrayList<Runway> listOfRunways = new ArrayList<Runway>();
		listOfRunways.add(new Runway(9, 'L', 3902, 3900, 3902, 3595, 306, 50));
		listOfRunways.add(new Runway(27, 'R', 3902, 3900, 3902, 3900, 0, 50));

		airport = new Airport(listOfRunways);
		gui.updateRunwayList(airport.getListOfRunways());
		gui.updateGraphicRunway();
		Runway currentRunway = airport.getCurrentRunway();
		// add the figures to the gui
		gui.setOriginalFigures(currentRunway.getToda(), currentRunway.getTora(), currentRunway.getLda(),
				currentRunway.getAsda());
	}

	public static void main(String[] args) {
		Controller c = new Controller();
	}
}
