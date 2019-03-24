package com.ikeyleap.ctrl;

import ca.odell.glazedlists.*;
import ca.odell.glazedlists.matchers.ThreadedMatcherEditor;
import ca.odell.glazedlists.gui.TableFormat;
import ca.odell.glazedlists.swing.EventTableModel;
import ca.odell.glazedlists.swing.TextComponentMatcherEditor;

import javax.swing.*;

import org.jdesktop.beansbinding.BeanProperty;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FilterListExample {

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

    private static final String[] musicalStrings =
            {"Seven", "Mary", "Three", "Alice", "In", "Chains",
             "Green", "Day", "Led", "Zeppelin", "Beatles", "Prince",
             "Holy", "Cake", "White", "Black", "Sgt.", "Pepper"};

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

    public static void main(String[] args) {
        // create an EventList of MP3s
        final EventList mp3s = new BasicEventList();

        // populate the MP3 list
        for (int i = 1; i <= 25000; i++) {
            String artist = makeRandomMusicString(2 + dice.nextInt(3));
            String album = makeRandomMusicString(2 + dice.nextInt(3));
            String song = makeRandomMusicString(2 + dice.nextInt(3));
            mp3s.add(new MP3(artist, album, song, i));
        }

        JTextField filterField = new JTextField(25);
        TextFilterator mp3Filterator = new TextFilterator() {
            public void getFilterStrings(List baseList, Object element) {
                MP3 mp3 = (MP3) element;
                baseList.add(mp3.getAlbum());
                baseList.add(mp3.getArtist());
                baseList.add(mp3.getSong());
            }
        };
        TextComponentMatcherEditor matcherEditor = new TextComponentMatcherEditor(filterField, mp3Filterator);
        FilterList filteredMP3s = new FilterList(mp3s, new ThreadedMatcherEditor(matcherEditor));

        // build a panel to hold the filter
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        filterPanel.add(new JLabel("Filter:"));
        filterPanel.add(filterField);

        // build a JTable
        String[] propertyNames = new String[] {"track", "artist", "album", "song"};
        String[] columnLabels = new String[] {"Track", "Artist", "Album", "Song"};
        TableFormat tf = GlazedLists.tableFormat(MP3.class, propertyNames, columnLabels);
        JTable t = new JTable(new EventTableModel(filteredMP3s, tf));
        t.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if (e.getClickCount() == 2) {
        			BeanProperty<JTable, Object> jTableBeanProperty = BeanProperty.create("selectedElement");
        			Object o = jTableBeanProperty.getValue(t);
        			System.out.println(">>>>>>>>>>>>>>>>>" + o);
        			BeanProperty.create("selectedElement");
        		}
        	}
        });

        // place the table in a JFrame
        JFrame f = new JFrame();
        f.getContentPane().setLayout(new BorderLayout());
        f.getContentPane().add(filterPanel, BorderLayout.NORTH);
        f.getContentPane().add(new JScrollPane(t), BorderLayout.CENTER);

        // show the frame
        f.pack();
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}