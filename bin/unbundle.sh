#!/bin/bash

SOURCE="${BASH_SOURCE[0]}"
while [ -h "$SOURCE" ]; do
  DIR="$(cd -P "$(dirname "$SOURCE")" && pwd)"
  SOURCE="$(readlink "$SOURCE")"
  [[ $SOURCE != /* ]] && SOURCE="$DIR/$SOURCE"
done
DIR="$(cd -P "$(dirname "$SOURCE")" && pwd)"


(
  cd ${DIR}/../..
  bundle_filename=$(ls secure-graph.*.bundle | tail -1)
  unlink secure-graph.bundle
  ln -s ${bundle_filename} secure-graph.bundle

  cd ${DIR}/..
  git pull
  echo "updated from ${bundle_filename}"
  git log -n 1 > ../${bundle_filename}.txt
)
