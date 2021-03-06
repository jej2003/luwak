package uk.co.flax.luwak.analysis;
/*
 *   Copyright (c) 2015 Lemur Consulting Ltd.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

import java.io.IOException;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.TermToBytesRefAttribute;
import org.apache.lucene.analysis.util.FilteringTokenFilter;
import org.apache.lucene.util.BytesRefHash;

/**
 * A FilteringTokenFilter that only accepts terms already contained in a BytesRefHash
 */
public class BytesRefFilteredTokenFilter extends FilteringTokenFilter {

    private final BytesRefHash termsHash;

    private final TermToBytesRefAttribute bytesAtt = addAttribute(TermToBytesRefAttribute.class);

    public BytesRefFilteredTokenFilter(TokenStream ts, BytesRefHash termsHash) {
        super(ts);
        this.termsHash = termsHash;
    }

    @Override
    protected boolean accept() throws IOException {
        return termsHash.find(bytesAtt.getBytesRef()) >= 0;
    }
}
