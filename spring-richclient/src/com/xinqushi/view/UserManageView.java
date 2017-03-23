/**
 * 
 */
package com.xinqushi.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Date;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.springframework.context.MessageSource;
import org.springframework.richclient.application.Application;
import org.springframework.richclient.application.ApplicationServicesLocator;
import org.springframework.richclient.application.support.AbstractView;
import org.springframework.richclient.command.ActionCommand;
import org.springframework.richclient.command.CommandGroup;
import org.springframework.richclient.dialog.TitledPageApplicationDialog;
import org.springframework.richclient.table.BeanTableModel;
import org.springframework.richclient.util.GuiStandardUtils;
import org.springframework.rules.closure.Closure;

import com.xinqushi.form.UserForm;
import com.xinqushi.form.UserQueryForm;
import com.xinqushi.model.User;

/**
 * 
 */
public class UserManageView extends AbstractView {

	private UserQueryForm queryForm;

	private JTable queryResultTable;

	private BeanTableModel queryResultModel;

	private ActionCommand addCommand;

	private ActionCommand modifyCommand;

	private ActionCommand deleteCommand;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.richclient.application.support.AbstractView#createControl
	 * ()
	 */
	@Override
	protected JComponent createControl() {
		initControls();
		JPanel pane = new JPanel(new BorderLayout());
		pane.add(queryForm.getControl(), BorderLayout.NORTH);
		pane.add(new JScrollPane(queryResultTable), BorderLayout.CENTER);
		pane.add(CommandGroup.createCommandGroup(
				new Object[] { addCommand, modifyCommand, deleteCommand })
				.createButtonBar(), BorderLayout.SOUTH);
		return GuiStandardUtils.attachBorder(pane);
	}

	private void initControls() {
		queryForm = new UserQueryForm();

		queryResultModel = new BeanTableModel(User.class,
				(MessageSource) ApplicationServicesLocator.services()
						.getService(MessageSource.class)) {

			@Override
			protected String[] createColumnPropertyNames() {
				return new String[] { "firstName", "lastName", "age",
						"registerDate", "remark" };
			}

			@Override
			protected Class[] createColumnClasses() {
				return new Class[] { String.class, String.class, Integer.class,
						Date.class, String.class };
			}
		};
		queryResultTable = new JTable(queryResultModel);

		addCommand = new ActionCommand("addCommand") {

			@Override
			protected void doExecuteCommand() {
				TitledPageApplicationDialog dialog = getDialog(new User(),
						new Closure() {

							public Object call(Object argument) {
								queryResultModel.addRow(argument);
								return null;
							}
						}, addCommand);
				dialog.showDialog();
			}
		};

		modifyCommand = new ActionCommand("modifyCommand") {

			@Override
			protected void doExecuteCommand() {
				if (queryResultTable.getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(Application.instance()
							.getActiveWindow().getControl(),
							"Please select a user.");
					return;
				}
				TitledPageApplicationDialog dialog = getDialog(
						(User) queryResultModel.getRow(queryResultTable
								.getSelectedRow()), new Closure() {

							public Object call(Object argument) {
								queryResultModel.fireTableRowsUpdated(
										queryResultTable.getSelectedRow(),
										queryResultTable.getSelectedRow());
								return null;
							}
						}, modifyCommand);
				dialog.showDialog();
			}
		};

		deleteCommand = new ActionCommand("delCommand") {

			@Override
			protected void doExecuteCommand() {
				//选择验证
				if (queryResultTable.getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(Application.instance()
							.getActiveWindow().getControl(),
							"Please select a user.");
					return;
				}
				//未实现
				/*TitledPageApplicationDialog dialog = getDialog((User) queryResultModel.getRow(queryResultTable
						.getSelectedRow()), new Closure() {

							public Object call(Object argument) {
								queryResultModel.fireTableRowsDeleted(queryResultTable.getSelectedRow(), queryResultTable.getSelectedRow());
								return null;
							}
						}, deleteCommand);
				dialog.showDialog();*/
			}
		};
	}

	private TitledPageApplicationDialog getDialog(User user,
			final Closure closure, ActionCommand callingCommand) {
		final UserForm form = new UserForm();
		form.setFormObject(user);
		TitledPageApplicationDialog dialog = new TitledPageApplicationDialog(
				form, Application.instance().getActiveWindow().getControl()) {

			@Override
			protected boolean onFinish() {
				form.commit();
				closure.call(form.getFormObject());
				return true;
			}
		};
		dialog.setCallingCommand(callingCommand);
		dialog.setPreferredSize(new Dimension(380, 180));
		return dialog;
	}

}
