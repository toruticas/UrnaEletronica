# este script teste o backend através do netcat
candidatos = [13, 45, 40, 50, 20, 43, 28, 16, 27, 21, 29]
while true
    aux = "888\n"
    candidatos.each { |e| aux += "#{e}|#{rand(0..100)}\n" }
    system "echo \"#{aux}\" | netcat 127.0.0.1 2202"
    sleep 1
end
