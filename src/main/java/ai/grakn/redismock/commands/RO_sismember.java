package ai.grakn.redismock.commands;

import ai.grakn.redismock.RedisBase;
import ai.grakn.redismock.Response;
import ai.grakn.redismock.Slice;
import ai.grakn.redismock.exception.InternalException;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static ai.grakn.redismock.Utils.deserializeObject;
import static ai.grakn.redismock.Utils.serializeObject;

class RO_sismember extends AbstractRedisOperation {
    RO_sismember(RedisBase base, List<Slice> params) {
        super(base, params,  null, 1, null);
    }

    @Override
	Slice response() {
        Slice key = params().get(0);
        Slice data = base().rawGet(key);
        Set<Slice> set;

        if (data != null) {
            set = deserializeObject(data);
        } else {
            set = new HashSet<>();
        }

        boolean contains = set.contains(params().get(1));

        return Response.integer(contains?1:0);
    }
}
