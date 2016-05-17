while true; do
    # echo "Hello World"
    cat config/urna1.txt | netcat 127.0.0.1 2202
    sleep 1
done
