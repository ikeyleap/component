package com.ikeyleap.ctrl.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import com.google.common.collect.Lists;
import com.ikeyleap.ctrl.component.model.ColModel;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.FilterList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.TextFilterator;
import ca.odell.glazedlists.gui.TableFormat;
import ca.odell.glazedlists.matchers.ThreadedMatcherEditor;
import ca.odell.glazedlists.swing.EventTableModel;
import ca.odell.glazedlists.swing.TextComponentMatcherEditor;
import cn.hutool.core.util.ReflectUtil;

@SuppressWarnings({ "serial", "deprecation" })
public class TestKXListTable extends JFrame {

	public static class MP3 {
		private int track;
		private String song;
		private String album;
		private String artist;

		public MP3(String artist, String album, String name, int track) {
			this.song = name;
			this.album = album;
			this.artist = artist;
			this.track = track;
		}

		public String getSong() {
			return song;
		}

		public String getAlbum() {
			return album;
		}

		public String getArtist() {
			return artist;
		}

		public int getTrack() {
			return track;
		}
	}

	private static final String[] musicalStrings = { "Seven", "Mary", "Three", "Alice", "In", "Chains", "Green", "Day",
			"Led", "Zeppelin", "Beatles", "Prince", "Holy", "Cake", "White", "Black", "Sgt.", "Pepper" };

	private static final Random dice = new Random();

	private static String makeRandomMusicString(int numParts) {
		StringBuffer musicBuffer = new StringBuffer();

		for (int i = 0; i < numParts; i++) {
			if (musicBuffer.length() > 0)
				musicBuffer.append(' ');
			musicBuffer.append(musicalStrings[dice.nextInt(musicalStrings.length)]);
		}

		return musicBuffer.toString();
	}

	@SuppressWarnings("unused")
	private JPanel contentPane;
	@SuppressWarnings("unused")
	private JTextField filterField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestKXListTable frame = new TestKXListTable();
					frame.pack();
					frame.setLocationRelativeTo(null);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	public TestKXListTable() {
		initialize();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {

		// create an EventList of MP3s
		final EventList dataList = new BasicEventList();

		// populate the MP3 list
		for (int i = 1; i <= 25000; i++) {
			String artist = makeRandomMusicString(2 + dice.nextInt(3));
			String album = makeRandomMusicString(2 + dice.nextInt(3));
			String song = makeRandomMusicString(2 + dice.nextInt(3));
			dataList.add(new MP3(artist, album, song, i));
		}

		JTextField filterField = new JTextField(25);

		// build a panel to hold the filter
		JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		filterPanel.add(new JLabel("Filter:"));
		filterPanel.add(filterField);

		// build a JTable		
		List<ColModel> tableList = Lists.newArrayList();

		tableList.add(new ColModel("track","Track"));
		tableList.add(new ColModel("artist", "Artist"));
		tableList.add(new ColModel("album", "Album"));
		tableList.add(new ColModel("song", "Song"));
		TableFormat tf = getTableFormat(tableList);
		
		// place the table in a JFrame
		setLayout(new BorderLayout());
		add(filterPanel, BorderLayout.NORTH);
		add(creatTable(tf, dataList, filterField), BorderLayout.CENTER);
	}
	
	@SuppressWarnings("rawtypes")
	public static TableFormat getTableFormat(List<ColModel> tableList){
		String[] propertyNames = new String[tableList.size()];
		String[] columnLabels = new String[tableList.size()];
		boolean[] editables = new boolean[tableList.size()];
		for (int i = 0; i < tableList.size(); i++) {
			propertyNames[i] = tableList.get(i).getPropertyNames();
			columnLabels[i] = tableList.get(i).getColumnLabels();
			editables[i] = tableList.get(i).getEditable().booleanValue();
		}
		TableFormat tf = GlazedLists.tableFormat(propertyNames, columnLabels, editables);
		return tf;
	}

	private static TextFilterator<Object> getTextFilterator() {
		TextFilterator<Object> Filterator = new TextFilterator<Object>() {

			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void getFilterStrings(List baseList, Object element) {
				baseList.addAll(Lists.newArrayList(ReflectUtil.getFieldsValue(element)));
			}

		};
		return Filterator;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private  static FilterList getFilterList(EventList eventList, JTextComponent filterField) {
		return new FilterList(eventList,
				new ThreadedMatcherEditor(new TextComponentMatcherEditor(filterField, getTextFilterator())));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static JTable creatTable(TableFormat tf, EventList eventList, JTextComponent filterField) {
		return new JTable(new EventTableModel(getFilterList(eventList, filterField), tf));
	}
	


}
