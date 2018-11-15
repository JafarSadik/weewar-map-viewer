// Parse command line arguments and options
parseCli = { args ->
    CliBuilder cli = new CliBuilder(usage: 'convert-maps.groovy [options] [output_dir]', header: 'Options:')
    cli.s(longOpt: 'silent', 'silent mode, no output to console', required: false)
    cli.h(longOpt: 'help', 'shows the help', required: false)
    cli.p(longOpt: 'pretty', 'convert maps to formatted json', required: false)
    cli.f(longOpt: 'force', 'force override target directory', required: false)
    cli.parse(args)
}

