/*
 * Copyright (c) 2012 The University of Manchester, UK.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * * Neither the names of The University of Manchester nor the names of its
 *   contributors may be used to endorse or promote products derived from this
 *   software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package uk.org.taverna.server.client;

import java.io.File;

import org.w3c.dom.Element;

/**
 * 
 * @author Robert Haines
 * 
 */
public final class InputPort extends Port {

	private String value;
	private File file;
	private boolean remoteFile;

	public InputPort(Run run, Element xml) {
		super(run, xml);

		value = null;
		file = null;
		remoteFile = false;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		if (run.isInitialized()) {
			file = null;
			remoteFile = false;
			this.value = value;
		}
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		if (run.isInitialized()) {
			value = null;
			remoteFile = false;
			this.file = file;
		}
	}

	public void setRemoteFile(File file) {
		if (run.isInitialized()) {
			value = null;
			this.file = file;
			remoteFile = true;
		}
	}

	public boolean isFile() {
		return !(file == null);
	}

	public boolean isRemoteFile() {
		return isFile() && remoteFile;
	}

	public boolean isBaclava() {
		return run.isBaclavaInput();
	}

	public boolean isSet() {
		return !(value == null) || isFile() || isBaclava();
	}
}
