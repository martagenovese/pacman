package myclasses;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.DocumentLoader;
import org.apache.batik.bridge.GVTBuilder;
import org.apache.batik.bridge.UserAgent;
import org.apache.batik.bridge.UserAgentAdapter;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.svg.SVGDocument;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SVGIcon implements Icon {
    private GraphicsNode svgIcon;

    public SVGIcon(String svgURI) {
        try {
            String parser = XMLResourceDescriptor.getXMLParserClassName();
            SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);
            SVGDocument document = factory.createSVGDocument(svgURI);

            // Print to check if the SVG document is created
            System.out.println("SVG Document: " + document);

            UserAgent userAgent = new UserAgentAdapter();
            DocumentLoader loader = new DocumentLoader(userAgent);
            BridgeContext bridgeContext = new BridgeContext(userAgent, loader);
            bridgeContext.setDynamicState(BridgeContext.DYNAMIC);

            GVTBuilder builder = new GVTBuilder();
            svgIcon = builder.build(bridgeContext, document);

            // Print to check if the GraphicsNode is built
            System.out.println("GraphicsNode: " + svgIcon);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g;
        svgIcon.paint(g2d);
        System.out.println("Painted");
    }

    public Image getImage() {
        BufferedImage image = new BufferedImage(getIconWidth(), getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        paintIcon(null, g2d, 0, 0);
        g2d.dispose();
        return image;
    }

    @Override
    public int getIconWidth() {
        return (int) svgIcon.getBounds().getWidth();
    }

    @Override
    public int getIconHeight() {
        return (int) svgIcon.getBounds().getHeight();
    }
}