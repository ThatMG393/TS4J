package com.thatmg393.android.ts4j.log;

import android.widget.TextView;
import io.github.rosemoe.sora.text.Content;
import io.github.rosemoe.sora.widget.CodeEditor;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class SoraEditorLogHandler extends Handler {
	private CodeEditor view;
		
	public SoraEditorLogHandler(CodeEditor view) {
		this.view = view;
	}
		
	@Override
	public void publish(LogRecord logRecord) {
		if (!isLoggable(logRecord)) return;
		view.post(() -> {
			Content c = view.getText();
			c.insert(c.getLineCount() - 1, 0, getFormatter().format(logRecord) + "\n");
		});
	}
	
	@Override
	public void flush() {
		// no-op
	}

	@Override
	public void close() throws SecurityException {
		view.post(() -> view.release());
	}
}
