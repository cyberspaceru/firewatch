package com.wiley.firewatch.utils;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by itatsiy on 4/23/2018.
 */
@Data
@Accessors(fluent = true)
public class ContentType {
    private String mimeType;
    private String charset;
    private String boundary;

    @Override
    public String toString() {
        return mimeType
                + (charset != null ? ";[\\s]{0,}charset=" + charset : "")
                + (boundary != null ? ";[\\s]{0,}boundary=" + boundary : "");
    }

    public ContentType utf8() {
        return this.charset("utf-8");
    }

    public ContentType windows1251() {
        return this.charset("windows-1251");
    }

    public ContentType usAscii() {
        return this.charset("US-ASCII");
    }

    public static ContentType aac() {
        return new ContentType().mimeType("audio/aac");
    }

    public static ContentType abw() {
        return new ContentType().mimeType("application/x-abiword");
    }

    public static ContentType arc() {
        return new ContentType().mimeType("application/octet-stream");
    }

    public static ContentType avi() {
        return new ContentType().mimeType("video/x-msvideo");
    }

    public static ContentType azw() {
        return new ContentType().mimeType("application/vnd.amazon.ebook");
    }

    public static ContentType bin() {
        return new ContentType().mimeType("application/octet-stream");
    }

    public static ContentType bz() {
        return new ContentType().mimeType("application/x-bzip");
    }

    public static ContentType bz2() {
        return new ContentType().mimeType("application/x-bzip2");
    }

    public static ContentType csh() {
        return new ContentType().mimeType("application/x-csh");
    }

    public static ContentType css() {
        return new ContentType().mimeType("text/css");
    }

    public static ContentType csv() {
        return new ContentType().mimeType("text/csv");
    }

    public static ContentType doc() {
        return new ContentType().mimeType("application/msword");
    }

    public static ContentType eot() {
        return new ContentType().mimeType("application/vnd.ms-fontobject");
    }

    public static ContentType epub() {
        return new ContentType().mimeType("application/epub+zip");
    }

    public static ContentType gif() {
        return new ContentType().mimeType("image/gif");
    }

    public static ContentType htm() {
        return new ContentType().mimeType("text/html");
    }

    public static ContentType html() {
        return new ContentType().mimeType("text/html");
    }

    public static ContentType ico() {
        return new ContentType().mimeType("image/x-icon");
    }

    public static ContentType ics() {
        return new ContentType().mimeType("text/calendar");
    }

    public static ContentType jar() {
        return new ContentType().mimeType("application/java-archive");
    }

    public static ContentType jpeg() {
        return new ContentType().mimeType("image/jpeg");
    }

    public static ContentType jpg() {
        return new ContentType().mimeType("image/jpeg");
    }

    public static ContentType appJs() {
        return new ContentType().mimeType("application/javascript");
    }

    public static ContentType textJs() {
        return new ContentType().mimeType("text/javascript");
    }

    public static ContentType json() {
        return new ContentType().mimeType("application/json");
    }

    public static ContentType mid() {
        return new ContentType().mimeType("audio/midi");
    }

    public static ContentType midi() {
        return new ContentType().mimeType("audio/midi");
    }

    public static ContentType mpeg() {
        return new ContentType().mimeType("video/mpeg");
    }

    public static ContentType mpkg() {
        return new ContentType().mimeType("application/vnd.apple.installer+xm");
    }

    public static ContentType odp() {
        return new ContentType().mimeType("application/vnd.oasis.opendocument.presentation");
    }

    public static ContentType ods() {
        return new ContentType().mimeType("application/vnd.oasis.opendocument.spreadsheet");
    }

    public static ContentType odt() {
        return new ContentType().mimeType("application/vnd.oasis.opendocument.text");
    }

    public static ContentType oga() {
        return new ContentType().mimeType("image/png");
    }

    public static ContentType ogv() {
        return new ContentType().mimeType("video/ogg");
    }

    public static ContentType ogx() {
        return new ContentType().mimeType("application/ogg");
    }

    public static ContentType otf() {
        return new ContentType().mimeType("font/otf");
    }

    public static ContentType png() {
        return new ContentType().mimeType("image/png");
    }

    public static ContentType pdf() {
        return new ContentType().mimeType("application/pdf");
    }

    public static ContentType ppt() {
        return new ContentType().mimeType("application/vnd.ms-powerpoint");
    }

    public static ContentType rar() {
        return new ContentType().mimeType("application/x-rar-compressed");
    }

    public static ContentType rtf() {
        return new ContentType().mimeType("application/rtf");
    }

    public static ContentType sh() {
        return new ContentType().mimeType("application/x-sh");
    }

    public static ContentType svg() {
        return new ContentType().mimeType("image/svg+xml");
    }

    public static ContentType swf() {
        return new ContentType().mimeType("application/x-shockwave-flash");
    }

    public static ContentType tar() {
        return new ContentType().mimeType("application/x-tar");
    }

    public static ContentType tif() {
        return new ContentType().mimeType("image/tiff");
    }

    public static ContentType tiff() {
        return new ContentType().mimeType("image/tiff");
    }

    public static ContentType ts() {
        return new ContentType().mimeType("video/vnd.dlna.mpeg-tts");
    }

    public static ContentType ttf() {
        return new ContentType().mimeType("font/ttf");
    }

    public static ContentType vsd() {
        return new ContentType().mimeType("application/vnd.visio");
    }

    public static ContentType wav() {
        return new ContentType().mimeType("audio/x-wav");
    }

    public static ContentType weba() {
        return new ContentType().mimeType("audio/webm");
    }

    public static ContentType webm() {
        return new ContentType().mimeType("video/webm");
    }

    public static ContentType webp() {
        return new ContentType().mimeType("image/webp");
    }

    public static ContentType woff() {
        return new ContentType().mimeType("font/woff");
    }

    public static ContentType woff2() {
        return new ContentType().mimeType("font/woff2");
    }

    public static ContentType xhtml() {
        return new ContentType().mimeType("application/xhtml+xml");
    }

    public static ContentType xls() {
        return new ContentType().mimeType("application/vnd.ms-excel");
    }

    public static ContentType xml() {
        return new ContentType().mimeType("application/xml");
    }

    public static ContentType xul() {
        return new ContentType().mimeType("application/vnd.mozilla.xul+xml");
    }

    public static ContentType zip() {
        return new ContentType().mimeType("application/zip");
    }

    public static ContentType $3gp() {
        return new ContentType().mimeType("video/3gpp");
    }

    public static ContentType $3gpWithoutVideo() {
        return new ContentType().mimeType("audio/3gpp");
    }

    public static ContentType $3g2() {
        return new ContentType().mimeType("video/3gpp2");
    }

    public static ContentType $3g2WithoutVideo() {
        return new ContentType().mimeType("audio/3gpp2");
    }

    public static ContentType $7z() {
        return new ContentType().mimeType("application/x-7z-compressed");
    }
}
