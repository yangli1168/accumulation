/**
 * 
 */
package com.xinqushi.form;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import org.springframework.binding.form.CommitListener;
import org.springframework.binding.form.FormModel;
import org.springframework.richclient.application.Application;
import org.springframework.richclient.command.CommandGroup;
import org.springframework.richclient.form.AbstractForm;
import org.springframework.richclient.form.FormModelHelper;
import org.springframework.richclient.form.builder.TableFormBuilder;
import com.xinqushi.model.UserQueryCondition;

/**
 *
 */
public class UserQueryForm extends AbstractForm implements CommitListener {

	public UserQueryForm(){
		super(FormModelHelper.createFormModel(new UserQueryCondition(), "UserQueryForm"),"UserQueryForm");
	}
	/* (non-Javadoc)
	 * @see org.springframework.richclient.form.AbstractForm#createFormControl()
	 */
	@Override
	protected JComponent createFormControl() {
		TableFormBuilder builder = new TableFormBuilder(getBindingFactory());
		builder.add("firstName");
		builder.add("lastName");
		builder.getLayoutBuilder().gapCol().row();
		builder.getLayoutBuilder().cell(CommandGroup.createCommandGroup(new Object[]{getCommitCommand()}).createButtonBar());
		
		this.attachFormErrorGuard(getCommitCommand());
		this.getFormModel().addCommitListener(this);
		
		return builder.getForm();
	}
	@Override
	protected String getCommitCommandFaceDescriptorId() {
		return "queryCommand";
	}
	@Override
	public void postCommit(FormModel formModel) {
		super.postCommit(formModel);
		JOptionPane.showMessageDialog(Application.instance().getActiveWindow().getControl(), "查询中 ...");
	}
}
