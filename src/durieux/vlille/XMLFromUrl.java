package durieux.vlille;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLFromUrl {
	private final String url;
	private Charset charset;

	public XMLFromUrl(final String url, final Charset charset) {
		super();
		this.url = url;
		this.charset = charset;
	}

	public XMLFromUrl(final String url) {
		super();
		this.url = url;
	}

	public Document parse() throws XMLParserException {
		try {
			final URL url = new URL(this.url);
			final DocumentBuilderFactory dbf = DocumentBuilderFactory
					.newInstance();
			final DocumentBuilder db = dbf.newDocumentBuilder();
			BufferedReader br = null;
			if (this.charset != null) {
				final BufferedReader in = new BufferedReader(
						new InputStreamReader(url.openStream(), this.charset));
				String content = "";
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					content += inputLine;
				}
				in.close();

				br = new BufferedReader(new StringReader(content));
			} else {
				br = new BufferedReader(new InputStreamReader(url.openStream()));
			}
			final InputSource is = new InputSource(br);

			final Document doc = db.parse(is);
			doc.getDocumentElement().normalize();

			return doc;
		} catch (final MalformedURLException e) {
			throw new XMLParserException(e);
		} catch (final ParserConfigurationException e) {
			throw new XMLParserException(e);
		} catch (final IOException e) {
			throw new XMLParserException(e);
		} catch (final SAXException e) {
			throw new XMLParserException(e);
		}
	}
}
