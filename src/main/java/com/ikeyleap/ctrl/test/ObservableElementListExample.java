package com.ikeyleap.ctrl.test;

import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.ObservableElementList;
import ca.odell.glazedlists.gui.TableFormat;
import ca.odell.glazedlists.swing.EventTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.ListIterator;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

public class ObservableElementListExample {

    public static class AmericanIdol {
        private String name;
        private int votes;

        private final PropertyChangeSupport support = new PropertyChangeSupport(this);

        public AmericanIdol(String name) {
            this.name = name;
        }

        public void addPropertyChangeListener(PropertyChangeListener l) {
            support.addPropertyChangeListener(l);
        }

        public void removePropertyChangeListener(PropertyChangeListener l) {
            support.removePropertyChangeListener(l);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            final String oldName = this.name;
            this.name = name;
            support.firePropertyChange("name", oldName, name);
        }

        public int getVotes() {
            return votes;
        }

        public void setVotes(int votes) {
            final int oldVotes = this.votes;
            this.votes = votes;
            support.firePropertyChange("votes", oldVotes, votes);
        }

        public void incrementVotes() {
            setVotes(getVotes() + 1);
        }
    }

    public static void main(String[] args) throws Exception {
        // create an EventList of AmericanIdols
        EventList<AmericanIdol> idols = GlazedLists.threadSafeList(new BasicEventList<AmericanIdol>());
        idols.add(new AmericanIdol("Simon Cowell"));
        idols.add(new AmericanIdol("Paula Abdul"));
        idols.add(new AmericanIdol("Randy Jackson"));
        idols.add(new AmericanIdol("Ryan Seacrest"));

        ObservableElementList.Connector<AmericanIdol> idolConnector = GlazedLists.beanConnector(AmericanIdol.class);
        EventList<AmericanIdol> observedIdols = new ObservableElementList<AmericanIdol>(idols, idolConnector);

        // build a JTable
        String[] propertyNames = {"name", "votes"};
        String[] columnLabels = {"Name", "Votes"};
        TableFormat<AmericanIdol> tf = GlazedLists.tableFormat(AmericanIdol.class, propertyNames, columnLabels);
        JTable t = new JTable(new EventTableModel<AmericanIdol>(observedIdols, tf));

        // place the table in a JFrame
        JFrame f = new JFrame();
        f.add(new JScrollPane(t), BorderLayout.CENTER);

        // show the frame
        f.pack();
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        // vote one idol off each episode
        while (idols.size() > 1) {
            // clear the votes
            for (ListIterator<AmericanIdol> i = idols.listIterator(); i.hasNext();) {
                AmericanIdol idol = i.next();
                idol.setVotes(0);
                Thread.sleep(300);
            }

            // simulating voting
            long quittingTime = System.currentTimeMillis() + 5000;
            Random dice = new Random();
            while (System.currentTimeMillis() < quittingTime) {
                int index = dice.nextInt(idols.size());
                AmericanIdol idol = idols.get(index);
                idol.incrementVotes();
                Thread.sleep(100);
            }

            // find the idol with least votes
            AmericanIdol votedOff = idols.get(0);
            for (AmericanIdol idol : idols) {
                if (idol.getVotes() < votedOff.getVotes())
                    votedOff = idol;
            }

            // vote off the idol with the least votes
            Thread.sleep(1000);
            idols.remove(votedOff);
            Thread.sleep(1000);
        }
    }
}