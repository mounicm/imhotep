/*
 * Copyright (C) 2014 Indeed Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
 package com.indeed.flamdex.api;

import org.apache.log4j.Logger;

/**
 * @author jplaisance
 */
public final class GenericIntTermDocIterator implements IntTermDocIterator {

    private static final Logger log = Logger.getLogger(GenericIntTermDocIterator.class);

    private final IntTermIterator termIterator;

    private final DocIdStream docIdStream;

    public GenericIntTermDocIterator(IntTermIterator termIterator, DocIdStream docIdStream) {
        this.termIterator = termIterator;
        this.docIdStream = docIdStream;
    }

    @Override
    public boolean nextTerm() {
        final boolean ret = termIterator.next();
        if (ret) {
            docIdStream.reset(termIterator);
        }
        return ret;
    }

    @Override
    public long term() {
        return termIterator.term();
    }

    @Override
    public int docFreq() {
        return termIterator.docFreq();
    }

    @Override
    public int fillDocIdBuffer(final int[] docIdBuffer) {
        return docIdStream.fillDocIdBuffer(docIdBuffer);
    }

    @Override
    public int nextDocs(final int[] docIdBuffer) {
        return fillDocIdBuffer(docIdBuffer);
    }

    @Override
    public void close() {
        termIterator.close();
        docIdStream.close();
    }
}
