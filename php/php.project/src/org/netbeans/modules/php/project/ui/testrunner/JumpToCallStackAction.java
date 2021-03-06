/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.netbeans.modules.php.project.ui.testrunner;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.netbeans.modules.php.api.util.FileUtils;
import org.netbeans.modules.php.spi.testing.locate.Locations;
import org.openide.filesystems.FileUtil;
import org.openide.util.NbBundle;

public class JumpToCallStackAction extends AbstractAction {
    private static final long serialVersionUID = -14558324203007090L;

    private final String callstackFrameInfo;
    private final Callback callback;


    public JumpToCallStackAction(String callstackFrameInfo, Callback callback) {
        assert callstackFrameInfo != null;
        this.callstackFrameInfo = callstackFrameInfo;
        this.callback = callback;

        String name = NbBundle.getMessage(JumpToCallStackAction.class, "LBL_GoToSource");
        putValue(NAME, name);
        putValue(SHORT_DESCRIPTION, name);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (callback != null) {
            Locations.Line location = callback.parseLocation(callstackFrameInfo);
            if (location != null) {
                FileUtils.openFile(FileUtil.toFile(location.getFile()), location.getLine());
            }
        }
    }

    //~ Inner classes

    public interface Callback {
        Locations.Line parseLocation(String callStack);
    }

}
