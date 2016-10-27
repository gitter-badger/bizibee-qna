package com.aripd.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public enum Resizer {
    NEAREST_NEIGHBOR {
        @Override
        public BufferedImage resize(BufferedImage source, int width, int height) {
            return commonResize(source, width, height, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        }
    },
    BILINEAR {
        @Override
        public BufferedImage resize(BufferedImage source, int width, int height) {
            return commonResize(source, width, height, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        }
    },
    BICUBIC {
        @Override
        public BufferedImage resize(BufferedImage source, int width, int height) {
            return commonResize(source, width, height, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        }
    },
    PROGRESSIVE_BILINEAR {
        @Override
        public BufferedImage resize(BufferedImage source, int width, int height) {
            return progressiveResize(source, width, height, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        }
    },
    PROGRESSIVE_BICUBIC {
        @Override
        public BufferedImage resize(BufferedImage source, int width, int height) {
            return progressiveResize(source, width, height, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        }
    },
    AVERAGE {
        @Override
        public BufferedImage resize(BufferedImage source, int width, int height) {
            Image img2 = source.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
            BufferedImage img = new BufferedImage(width, height, source.getType());
            Graphics2D g = img.createGraphics();
            try {
                g.drawImage(img2, 0, 0, width, height, null);
            } finally {
                g.dispose();
            }
            return img;
        }
    };

    public abstract BufferedImage resize(BufferedImage source, int width, int height);

    private static BufferedImage progressiveResize(BufferedImage source, int width, int height, Object hint) {
        int w = Math.max(source.getWidth() / 2, width);
        int h = Math.max(source.getHeight() / 2, height);
        BufferedImage img = commonResize(source, w, h, hint);
        while (w != width || h != height) {
            BufferedImage prev = img;
            w = Math.max(w / 2, width);
            h = Math.max(h / 2, height);
            img = commonResize(prev, w, h, hint);
            prev.flush();
        }
        return img;
    }

    private static BufferedImage commonResize(BufferedImage source, int width, int height, Object hint) {
        BufferedImage img = new BufferedImage(width, height, source.getType());
        Graphics2D g = img.createGraphics();
        try {
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
            g.drawImage(source, 0, 0, width, height, null);
        } finally {
            g.dispose();
        }
        return img;
    }

    /**
     * This utility class draws and scales an image to fit canvas of a
     * component. if the image is smaller than the canvas, it is kept as it is.
     *
     * @param source BufferedImage
     * @param width int
     * @param height int
     * @param hint Object
     * @return BufferedImage
     */
    private static BufferedImage autoScale(BufferedImage source, int width, int height, Object hint) {
        int imgWidth = source.getWidth();
        int imgHeight = source.getHeight();

        double imgAspect = (double) imgHeight / imgWidth;

        double canvasAspect = (double) height / width;

        int x1 = 0; // top left X position
        int y1 = 0; // top left Y position
        int x2 = 0; // bottom right X position
        int y2 = 0; // bottom right Y position

        if (imgWidth < width && imgHeight < height) {
            // the image is smaller than the canvas
            x1 = (width - imgWidth) / 2;
            y1 = (height - imgHeight) / 2;
            x2 = imgWidth + x1;
            y2 = imgHeight + y1;

        } else {
            if (canvasAspect > imgAspect) {
                y1 = height;
                // keep image aspect ratio
                height = (int) (width * imgAspect);
                y1 = (y1 - height) / 2;
            } else {
                x1 = width;
                // keep image aspect ratio
                width = (int) (height / imgAspect);
                x1 = (x1 - width) / 2;
            }
            x2 = width + x1;
            y2 = height + y1;
        }

        BufferedImage img = new BufferedImage(width, height, source.getType());
        Graphics2D g = img.createGraphics();
        try {
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
            g.drawImage(source, x1, y1, x2, y2, 0, 0, imgWidth, imgHeight, null);
        } finally {
            g.dispose();
        }
        return img;
    }

};
