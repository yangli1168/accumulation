package com.xinqushi.form;

import java.sql.Date;

import javax.swing.JComponent;

import org.springframework.richclient.form.AbstractForm;
import org.springframework.richclient.form.FormModelHelper;
import org.springframework.richclient.form.binding.swing.TextComponentBinder;
import org.springframework.richclient.form.builder.TableFormBuilder;

import com.xinqushi.binding.DateSelectorBinding;
import com.xinqushi.model.User;

public class UserForm extends AbstractForm {

	public UserForm(){
		super(FormModelHelper.createFormModel(new User(), "UserForm"),"UserForm");
		// RulesValidator validator = new RulesValidator(getFormModel());
		// validator.setRulesContextId("rulesContextId");
		// getFormModel().setValidator(validator);
	}
	
	@Override
	protected JComponent createFormControl() {
		TableFormBuilder builder = new TableFormBuilder(getBindingFactory());
		builder.add("firstName");
		builder.add("lastName");
		builder.row();
		builder.add("age");
		builder.row();
		builder.add(new DateSelectorBinding(getFormModel(),"registerDate"));
		builder.row();
		builder.addTextArea("remark");
		return builder.getForm();
	}

}
