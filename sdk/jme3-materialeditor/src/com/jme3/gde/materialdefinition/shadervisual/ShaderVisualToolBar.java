/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jme3.gde.materialdefinition.shadervisual;

import com.jme3.gde.materialdefinition.icons.Icons;
import com.jme3.shader.Shader;

/**
 *
 * @author Nehon
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class ShaderVisualToolBar extends javax.swing.JPanel {

    private MatDefShaderElement parent;

    /**
     * Creates new form ShaderVisualToolBar
     */
    public ShaderVisualToolBar() {
        initComponents();
    }

    public void setParent(MatDefShaderElement parent) {
        this.parent = parent;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroup = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        versionList = new javax.swing.JComboBox();
        vertButton = new javax.swing.JToggleButton();
        fragButton = new javax.swing.JToggleButton();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(ShaderVisualToolBar.class, "ShaderVisualToolBar.jLabel1.text")); // NOI18N

        versionList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "GLSL100", "GLSL150" }));
        versionList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                versionListActionPerformed(evt);
            }
        });

        btnGroup.add(vertButton);
        vertButton.setIcon(Icons.vert);
        vertButton.setSelected(true);
        vertButton.setToolTipText(org.openide.util.NbBundle.getMessage(ShaderVisualToolBar.class, "ShaderVisualToolBar.vertButton.toolTipText")); // NOI18N
        vertButton.setIconTextGap(0);
        vertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vertButtonActionPerformed(evt);
            }
        });

        btnGroup.add(fragButton);
        fragButton.setIcon(Icons.frag);
        fragButton.setToolTipText(org.openide.util.NbBundle.getMessage(ShaderVisualToolBar.class, "ShaderVisualToolBar.fragButton.toolTipText")); // NOI18N
        fragButton.setActionCommand(org.openide.util.NbBundle.getMessage(ShaderVisualToolBar.class, "ShaderVisualToolBar.fragButton.actionCommand")); // NOI18N
        fragButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fragButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(vertButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fragButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(versionList, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(154, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fragButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(versionList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(vertButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void vertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vertButtonActionPerformed
        parent.refresh();
    }//GEN-LAST:event_vertButtonActionPerformed

    private void fragButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fragButtonActionPerformed
        parent.refresh();
    }//GEN-LAST:event_fragButtonActionPerformed

    private void versionListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_versionListActionPerformed
        parent.refresh();
    }//GEN-LAST:event_versionListActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGroup;
    private javax.swing.JToggleButton fragButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JComboBox versionList;
    private javax.swing.JToggleButton vertButton;
    // End of variables declaration//GEN-END:variables

    public String getVersion() {
        return versionList.getSelectedItem().toString();
    }

    public Shader.ShaderType getType() {
        if (btnGroup.isSelected(vertButton.getModel())) {
            return Shader.ShaderType.Vertex;
        } else {
            return Shader.ShaderType.Fragment;
        }
    }
}