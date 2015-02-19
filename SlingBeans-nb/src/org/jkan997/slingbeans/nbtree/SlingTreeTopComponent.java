/**
 * SlingBeans - NetBeans Sling plugin https://github.com/jkan997/SlingBeans Licensed under Apache 2.0 license http://www.apache.org/licenses/LICENSE-2.0
 */
package org.jkan997.slingbeans.nbtree;

import java.beans.PropertyVetoException;
import org.jkan997.slingbeans.helper.LogHelper;
import org.jkan997.slingbeans.nbservices.SlingFsConnector;
import org.jkan997.slingbeans.helper.StringHelper;
import org.jkan997.slingbeans.slingfs.FileObject;
import org.jkan997.slingbeans.slingfs.FileSystem;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.explorer.view.BeanTreeView;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.Lookups;
import org.openide.windows.TopComponent;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//org.jkan997.slingbeans.nbtree//SlingTree//EN",
        autostore = false)
@TopComponent.Description(
        preferredID = "SlingTreeTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "explorer", openAtStartup = true)
@ActionID(category = "Window", id = "org.jkan997.slingbeans.nbtree.SlingTreeTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "Sling Explorer",
        preferredID = "SlingTreeTopComponent")
/*@Messages({
 "CTL_CrxTreeAction=CrxTree",
 "CTL_CrxTreeTopComponent=CrxTree Window",
 "HINT_CrxTreeTopComponent=This is a CrxTree window"
 })*/
public final class SlingTreeTopComponent extends TopComponent implements ExplorerManager.Provider {

    protected FileObject rootFo = null;
    private final ExplorerManager mgr = new ExplorerManager();
    private final InstanceContent content = new InstanceContent();
    private final SlingRootNode rootNode;

    public SlingTreeTopComponent() {
        initComponents();
        associateLookup(ExplorerUtils.createLookup(mgr, getActionMap()));
        setDisplayName("Sling Explorer");
        pathPanel.setOpaque(false);
        pathText.setEnabled(false);
        //pathText.setEditable(false);
        pathText.setText("/");
        rootNode = new SlingRootNode(Children.LEAF);
        BeanTreeView btv = (BeanTreeView) crxScrollPane;
        rootNode.setHidden(true);
        rootNode.setBeanTreeView(btv);
        mgr.setRootContext(rootNode);
        Lookups.singleton(this);
    }

    private void initSlingFs(final FileObject rootFo) {
        try {
            final SlingNodeChildren rootChildren = new SlingNodeChildren(rootFo);

            SlingRootNode slingRootNode = new SlingRootNode(rootChildren);
            slingRootNode.setRootFileObject(rootFo);
            mgr.setRootContext(slingRootNode);
            pathText.setEnabled(true);
            crxScrollPane.invalidate();

        } catch (Exception ex) {
            LogHelper.logError(ex);
        }
    }
    protected SlingNode selectedSlingNode = null;

    private BeanTreeView getBeanTreeView() {

        BeanTreeView res = new BeanTreeView() {
            @Override
            public void selectionChanged(Node[] nodes, ExplorerManager em) throws PropertyVetoException {
                super.selectionChanged(nodes, em);
                if ((nodes != null) && (nodes.length > 0) && (nodes[0] instanceof SlingNode)) {
                    SlingNode slingNode = (SlingNode) (nodes[0]);
                    selectedSlingNode = slingNode;
                    if (slingNode != null) {
                        pathText.setText("/" + slingNode.getPath());
                    }
                }

            }
        };

        return res;
    }

    public ExplorerManager getExplorerManager() {
        return mgr;
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pathPanel = new javax.swing.JPanel();
        connectBtn = new javax.swing.JButton();
        gotoBtn = new javax.swing.JButton();
        pathText = new javax.swing.JTextField();
        disconnectBtn = new javax.swing.JButton();
        crxScrollPane = getBeanTreeView();

        pathPanel.setBackground(new java.awt.Color(0, 0, 0));

        org.openide.awt.Mnemonics.setLocalizedText(connectBtn, org.openide.util.NbBundle.getMessage(SlingTreeTopComponent.class, "SlingTreeTopComponent.connectBtn.text")); // NOI18N
        connectBtn.setMargin(new java.awt.Insets(2, 0, 2, 0));
        connectBtn.setMaximumSize(new java.awt.Dimension(75, 20));
        connectBtn.setMinimumSize(new java.awt.Dimension(75, 20));
        connectBtn.setPreferredSize(new java.awt.Dimension(75, 20));
        connectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectBtnActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(gotoBtn, org.openide.util.NbBundle.getMessage(SlingTreeTopComponent.class, "SlingTreeTopComponent.gotoBtn.text")); // NOI18N
        gotoBtn.setToolTipText(org.openide.util.NbBundle.getMessage(SlingTreeTopComponent.class, "SlingTreeTopComponent.gotoBtn.toolTipText")); // NOI18N
        gotoBtn.setMargin(new java.awt.Insets(2, 0, 2, 0));
        gotoBtn.setMaximumSize(new java.awt.Dimension(75, 20));
        gotoBtn.setMinimumSize(new java.awt.Dimension(75, 20));
        gotoBtn.setPreferredSize(new java.awt.Dimension(75, 20));
        gotoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gotoBtnActionPerformed(evt);
            }
        });

        pathText.setText(org.openide.util.NbBundle.getMessage(SlingTreeTopComponent.class, "SlingTreeTopComponent.pathText.text")); // NOI18N
        pathText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pathTextKeyPressed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(disconnectBtn, org.openide.util.NbBundle.getMessage(SlingTreeTopComponent.class, "SlingTreeTopComponent.disconnectBtn.text")); // NOI18N
        disconnectBtn.setMargin(new java.awt.Insets(2, 0, 2, 0));
        disconnectBtn.setMaximumSize(new java.awt.Dimension(75, 20));
        disconnectBtn.setMinimumSize(new java.awt.Dimension(75, 20));
        disconnectBtn.setPreferredSize(new java.awt.Dimension(75, 20));
        disconnectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disconnectBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pathPanelLayout = new javax.swing.GroupLayout(pathPanel);
        pathPanel.setLayout(pathPanelLayout);
        pathPanelLayout.setHorizontalGroup(
            pathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pathPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pathText, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(connectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(disconnectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gotoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pathPanelLayout.setVerticalGroup(
            pathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pathPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(connectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(gotoBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pathText, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(disconnectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pathPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crxScrollPane)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pathPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(crxScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void connectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectBtnActionPerformed
        SlingFsConnector sfh = new SlingFsConnector() {
            @Override
            protected void rootNodeHandler(FileObject fo) {
                rootFo = fo;
                try {
                    FileSystem fs = fo.getFileSystem();
                    rootNode.setName(fs.getDisplayName());
                } catch (Exception ex) {
                    LogHelper.logError(ex);
                }

            }
        };
        sfh.connectToFileSystem(null);
        this.initSlingFs(rootFo);
        rootFo = null;
    }//GEN-LAST:event_connectBtnActionPerformed
    private void gotoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gotoBtnActionPerformed
        String path = pathText.getText();
        gotoPath(path);
        //btv.expandNode(selectedSlingNode);
//selectedSlingNode.refresh();
    }//GEN-LAST:event_gotoBtnActionPerformed

    private void pathTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pathTextKeyPressed
        if (evt.getKeyCode() == 10) {
            gotoBtnActionPerformed(null);
        }

    }//GEN-LAST:event_pathTextKeyPressed

    public void gotoPath(String path) {
        BeanTreeView btv = (BeanTreeView) crxScrollPane;
        path = StringHelper.normalizePath(path);
        String[] pathArr = path.split("/");
        Node node = mgr.getRootContext();
        boolean childFound = false;
        LogHelper.logInfo(this, "Trying to expand path %s", path);
        for (String part : pathArr) {
            childFound = false;
            for (Node child : node.getChildren().getNodes()) {
                String nodeName = child.getDisplayName();
                LogHelper.logInfo(this, "Checking node %s", nodeName);
                if (nodeName.equals(part)) {
                    childFound = true;
                    btv.expandNode(child);
                    node = child;

                    LogHelper.logInfo(this, "Expanding %s", part);
                    break;
                }
            }
            if (!childFound) {
                LogHelper.logInfo(this, "Cannot expand %s", part);
                break;
            }
        }
        if (node != null) {
            try {
                mgr.setSelectedNodes(new Node[]{node});
            } catch (PropertyVetoException ex) {
                LogHelper.logError(ex);
            }
        }
    }

    private void disconnectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disconnectBtnActionPerformed
        rootFo = null;
        this.initSlingFs(rootFo);

    }//GEN-LAST:event_disconnectBtnActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton connectBtn;
    private javax.swing.JScrollPane crxScrollPane;
    private javax.swing.JButton disconnectBtn;
    private javax.swing.JButton gotoBtn;
    private javax.swing.JPanel pathPanel;
    private javax.swing.JTextField pathText;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }
}