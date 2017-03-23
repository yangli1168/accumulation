/**
 * 
 */
package com.xinqushi.binding;

import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;

import org.springframework.binding.form.FormModel;
import org.springframework.richclient.form.binding.support.AbstractBinding;

/**
 * 
 */
public class DateSelectorBinding extends AbstractBinding implements
		PropertyChangeListener, ItemListener {

	@SuppressWarnings("rawtypes")
	private JComboBox year;

	@SuppressWarnings("rawtypes")
	private JComboBox month;

	@SuppressWarnings("rawtypes")
	private JComboBox day;

	public DateSelectorBinding(FormModel formModel, String formPropertyPath) {
		super(formModel, formPropertyPath, Date.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.springframework.richclient.form.binding.support.AbstractBinding#
	 * doBindControl()
	 */
	@Override
	protected JComponent doBindControl() {
		JPanel pane = initControls();
		initDatas();
		initListener();
		refreshControl((Date) getValue());
		return pane;
	}

	private JPanel initControls() {
		JPanel pane = new JPanel(new GridLayout());
		year = createSelector();
		month = createSelector();
		day = createSelector();
		pane.add(year);
		pane.add(month);
		pane.add(day);
		return pane;
	}

	private void initListener() {
		getValueModel().addValueChangeListener(this);
		year.addItemListener(this);
		month.addItemListener(this);
		day.addItemListener(this);
	}

	@SuppressWarnings("rawtypes")
	private JComboBox createSelector() {
		return new JComboBox();
	}

	@SuppressWarnings("unchecked")
	private void initDatas() {
		Calendar now = Calendar.getInstance();
		year.addItem(null);
		for (int i = -5; i < 5; i++) {
			year.addItem(now.get(Calendar.YEAR) + i);
		}
		for (int i = 1; i < 13; i++) {
			month.addItem(i);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.springframework.richclient.form.binding.support.AbstractBinding#
	 * enabledChanged()
	 */
	@Override
	protected void enabledChanged() {
		year.setEnabled(!isReadOnly() && isEnabled());
		month.setEnabled(!isReadOnly() && isEnabled());
		day.setEnabled(!isReadOnly() && isEnabled());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.springframework.richclient.form.binding.support.AbstractBinding#
	 * readOnlyChanged()
	 */
	@Override
	protected void readOnlyChanged() {
		enabledChanged();
	}

	public void propertyChange(PropertyChangeEvent event) {
		refreshControl((Date) event.getNewValue());
	}

	private void refreshControl(Date value) {
		if (value != null) {
			Calendar date = Calendar.getInstance();
			date.setTime(value);
			year.setSelectedItem(date.get(Calendar.YEAR));
			month.setSelectedItem(date.get(Calendar.MONTH) + 1);
			day.setSelectedItem(date.get(Calendar.DAY_OF_MONTH));
		} else {
			year.setSelectedItem(null);
			month.setSelectedItem(null);
		}
	}

	private void refreshModel() {
		if (year.getSelectedItem() != null && month.getSelectedItem() != null
				&& day.getSelectedItem() != null) {
			Calendar date = Calendar.getInstance();
			date.set(Calendar.YEAR, ((Integer) year.getSelectedItem()));
			date.set(Calendar.MONTH, ((Integer) month.getSelectedItem()) - 1);
			date.set(Calendar.DAY_OF_MONTH, ((Integer) day.getSelectedItem()));
			getValueModel().setValueSilently(date.getTime(),
					DateSelectorBinding.this);
		} else {
			getValueModel().setValueSilently(null, DateSelectorBinding.this);
		}
	}

	@SuppressWarnings("unchecked")
	public void itemStateChanged(ItemEvent event) {
		if (event.getSource() == month) {
			day.removeAllItems();
			if (event.getStateChange() == ItemEvent.SELECTED
					&& event.getItem() != null) {
				Calendar date = Calendar.getInstance();
				date.set(Calendar.YEAR, (Integer) year.getSelectedItem());
				date.set(Calendar.MONTH, (Integer) event.getItem() - 1);
				int days = date.getActualMaximum(Calendar.DAY_OF_MONTH);
				for (int i = 1; i <= days; i++) {
					day.addItem(i);
				}
			}
		}
		refreshModel();
	}
}
